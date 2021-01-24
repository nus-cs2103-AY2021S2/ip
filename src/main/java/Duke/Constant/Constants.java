package Duke.Constant;

/**
 * A class containing all the constants used for this application.
 */
public class Constants {
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String FILE_PATH = "data/duke.txt";
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String START = "Hello from\n" + LOGO;
    public static final String INVALID_DATETIME_FORMAT = "Invalid format for date and time." + "\n" +
            "Your date (and time) should have format yyyy-mm-dd (HH-MM)" +
            "For example: 2019-10-15 or 2019-10-15 1800";
    public static final String MARK_DONE_TASK = "Nice! I've marked this task as done:\n  ";
    public static final String DELETE_TASK_SUCCESS = "Noted. I've removed this task: \n";
    public static final String ADD_TASK_SUCCESS = "Got it. I've added this task: \n";
}
