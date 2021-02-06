
public class Parser {

    /**
     * Parse the input string into recognisable command.
     *
     * @param string input.
     * @return command.
     */
    public static Command getCommand(String string) {
        String action;
        String info;
        Command command;

        if (string.contains(" ")) {
            String[] str = string.split(" ", 2);
            action = str[0];
            info = str[1];
        } else {
            action = string;
            info = "";
        }

        switch (action) {
        case "todo":
            command = new TodoCommand(info);
            break;
        case "deadline":
            command = new DeadlineCommand(info);
            break;
        case "event":
            command = new EventCommand(info);
            break;
        case "done":
            command = new DoneCommand(info);
            break;
        case "delete":
            command = new DeleteCommand(info);
            break;
        case "bye":
            command = new ExitCommand(info);
            break;
        case "list":
            command = new ListCommand(info);
            break;
        case "find":
            command = new FindCommand(info);
            break;
        case "date":
            command = new DateCommand(info);
            break;
        default:
            command = new ErrorCommand(info);
        }

        return command;
    }

    /**
     * Parse the information string to get the task required.
     *
     * @param info details.
     * @return task.
     */
    public static String getTask(String info) {
        String task;

        if (info.contains(" /")) {
            String[] str = info.split(" /", 2);
            task = str[0];
        } else {
            task = info;
        }

        return task;
    }

    /**
     * Parse the information string to get the timing for event.
     *
     * @param info detail.
     * @return time at.
     */
    public static String getTimeAt(String info) {
        String time;

        if (info.contains(" /at ")) {
            String[] str = info.split(" /at ", 2);
            time = str[1];
        } else {
            time = "";
        }

        return time;
    }

    /**
     * Parse the information string to get the timing for deadline.
     *
     * @param info detail.
     * @return time by.
     */
    public static String getTimeBy(String info) {
        String time;

        if (info.contains(" /by ")) {
            String[] str = info.split(" /at ", 2);
            time = str[1];
        } else {
            time = "";
        }

        return time;
    }

}
