//TODO consider using enum
public class Parser {
    public TaskList taskList;
    public boolean isAlive;

    public Parser (TaskList taskList) {
        this.taskList = taskList;
        this.isAlive = true;
    }

    public void executeCommand(String command) throws DukeException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];
        String time;
        switch (firstWord) {
            case "bye":
                this.isAlive = false;
                break;
            case "list":
                taskList.displayTasks();
                break;
            case "done":
                try {
                    String num = arr[1];
                    //TODO exception handling if num is not a number
                    taskList.markAsDone(Integer.valueOf(num));
                } catch (NumberFormatException e) {
                    throw new DukeException("Enter an integer only");
                }

                break;
            case "delete":
                try {
                    String num = arr[1];
                    taskList.deleteTask(Integer.valueOf(num));
                } catch (NumberFormatException e) {
                    throw new DukeException("Enter an integer only");
                }

                break;
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException("Sorry, description of a todo cannot be empty");
                }
                String toDo = arr[1];
                taskList.addTask(new ToDo(toDo));
                break;
            case "deadline":
                if (arr.length == 1) {
                    throw new DukeException("Sorry description of a deadline cannot be empty");
                }
                String deadline = arr[1];
                taskList.addTask(new Deadline(getMsg(deadline, " /by "), getDateTime(deadline, " /by ")));
                break;
            case "event":
                if (arr.length == 1) {
                    throw new DukeException("Sorry description of an event cannot be empty");
                }
                String event = arr[1];
                taskList.addTask(new Event(getMsg(event, " /at "), getDateTime(event, " /at ")));
                break;
            default:
                taskList.complain();

        }
    }


    public String getDateTime(String text, String search) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        if (index == -1) {
            throw new DukeException("Statement does not contain " + search);
        }
        return text.substring(index + 5);
    }

    public String getMsg(String text, String search) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        if (index == -1) {
            throw new DukeException("Statement does not contain " + search);
        }
        return text.substring(0, index);
    }
}
