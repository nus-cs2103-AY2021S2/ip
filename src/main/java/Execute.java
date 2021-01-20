//TODO consider using enum
public class Execute {
    public DukeBot bot;

    public Execute (DukeBot bot) {
        this.bot = bot;
    }

    public void executeCommand(String command) throws DukeException {
        String arr[] = command.split(" ", 2);
        String firstWord = arr[0];
        String time;
        switch (firstWord) {
            case "bye":
                bot.exit();
                break;
            case "list":
                bot.displayTasks();
                break;
            case "done":
                try {
                    String num = arr[1];
                    //TODO exception handling if num is not a number
                    bot.markAsDone(Integer.valueOf(num));
                } catch (NumberFormatException e) {
                    throw new DukeException("\t\tEnter an integer only");
                }

                break;
            case "delete":
                try {
                    String num = arr[1];
                    bot.deleteTask(Integer.valueOf(num));
                } catch (NumberFormatException e) {
                    throw new DukeException("\t\tEnter an integer only");
                }

                break;
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException("\t\tSorry description of a todo cannot be empty");
                }
                String toDo = arr[1];
                bot.addTask(new ToDo(toDo));
                break;
            case "deadline":
                if (arr.length == 1) {
                    throw new DukeException("\t\tSorry description of a deadline cannot be empty");
                }
                String deadline = arr[1];
                bot.addTask(new Deadline(getMsg(deadline, " /by "), getDateTime(deadline, " /by ")));
                break;
            case "event":
                if (arr.length == 1) {
                    throw new DukeException("\t\tSorry description of an event cannot be empty");
                }
                String event = arr[1];
                bot.addTask(new Event(getMsg(event, " /at "), getDateTime(event, " /at ")));
                break;
            default:
                bot.complain();

        }
    }


    private String getDateTime(String text, String search) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        if (index == -1) {
            throw new DukeException("\t\tStatement does not contain " + search);
        }
        return text.substring(index + 5);
    }

    private String getMsg(String text, String search) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(search);
        if (index == -1) {
            throw new DukeException("\t\tStatement does not contain " + search);
        }
        return text.substring(0, index);
    }
}
