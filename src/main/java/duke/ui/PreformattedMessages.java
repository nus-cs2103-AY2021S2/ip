package duke.ui;

public class PreformattedMessages {
    /** Main program logo */
    public static final String WELCOME_SCREEN =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n\n";
    private static final String DUKE_WELCOME = "Welcome to Duke!\n\n";

    /**
     * Get load screen upon successful task list load.
     *
     * If number of tasks is 0, message printed assumed no task list exists.
     * Otherwise, number of tasks is also printed.
     *
     * @param numTasks Number of tasks in loaded list.
     */
    public static String getLoadingSuccessMessage(int numTasks) {
        if (numTasks == 0) {
            return PreformattedMessages.DUKE_WELCOME
                    + "No existing tasks found.\n"
                    + "A new task list has been created to get you started :)";
        } else {
            return PreformattedMessages.DUKE_WELCOME
                    + "Existing task list loaded successfully.\n"
                    + "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in your list.";
        }
    }

    /**
     * Prints load screen upon failure to write to file.
     *
     * For read-only file access by user - task list can still be loaded.
     * Indication to user that file cannot be written to, and serves as important
     * signal to user that changes are not preserved.
     *
     * @param numTasks Number of tasks in loaded list.
     */
    public static String getFileWriteErrorMessage(int numTasks) {
        return PreformattedMessages.DUKE_WELCOME
                + "Warning: Destination file cannot be written to.\n"
                + "Existing task list loaded, but changes will not be saved.\n\n"
                + "You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in your list.";
    }

    /**
     * Prints load screen upon failure to load file.
     *
     * Occurs when directory cannot be created, or when file cannot be created
     * or read.
     */
    public static String getFileReadErrorMessage() {
        return PreformattedMessages.DUKE_WELCOME
                + "Warning: Destination file cannot be created/read.\n"
                + "New task list created, but changes will not be saved.";
    }

    /**
     * Prints load screen upon failure to load list.
     *
     * Occurs when file can be read, but the contents cannot be parsed
     * as a task list.
     */
    public static String getFileLoadErrorMessage() {
        return PreformattedMessages.DUKE_WELCOME
                + "Warning: Existing task list cannot be loaded.\n"
                + "A new task list has been created.";
    }
}
