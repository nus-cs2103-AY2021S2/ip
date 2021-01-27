/**
 * Parser class is an abstraction of an object that parses and interprets text messages into commands.
 */

public class Parser {
    public TaskList taskList;
    public boolean isAlive;

    /**
     * Instantiates the Parser with attributes.
     * @param taskList the task list with a list of commands
     */
    public Parser (TaskList taskList) {
        this.taskList = taskList;
        this.isAlive = true;
    }

    /**
     * This parses and executes relevant comments from the tasklist
     * @param command the command given by the user
     * @throws DukeException an exception due to errors in parsing text
     */
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
