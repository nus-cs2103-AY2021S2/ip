package duke;

/**
 * Handles the welcome and exit portions of the chatbot
 */
public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {

    }

    /**
     * Greets the user when the chatbot is ran
     */
    public static String greet() {
        return "Hello from\n" + logo + "Hello! I'm Duke" + "\n" + "Type help to get started!";
    }

    /**
     * Show instructions to the user on how to use duke
     * @return a list of command and how they work
     */
    public static String helpGuide() {
        StringBuilder sb = new StringBuilder();
        sb.append("These are the available commands: \n");
        sb.append("event TASKNAME /at yyyy-MM-dd start time-end time"
                + "eg: event project meeting /at 2019-12-01 2-4pm) \n");
        sb.append("deadline TASKNAME /by yyyy-MM-dd expected end time "
                + "(eg: deadline project meeting /by 2019-12-01 4pm) \n");
        sb.append("todo TASKNAME (eg: todo read book) \n");
        sb.append("done + taskNumber (eg: done 1) \n");
        sb.append("delete + taskNumber (eg: delete 1) \n");
        sb.append("list : list all your existing task \n");
        sb.append("save : save your current tasks \n");
        sb.append("load : load your existing tasks \n");
        sb.append("bye : exit the program \n");
        return sb.toString();
    }

    /**
     * Displays exit message when user exits the chatbot
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
