package tlylt.haha;

/**
 * Representation of all user interaction.
 */
public class Ui {
    private static final String LINE_BREAK =
            "____________________________________________________________\n";
    private static final String LOGO = " _    _          _    _\n"
            + "| |  | |   /\\   | |  | |   /\\\n"
            + "| |__| |  /  \\  | |__| |  /  \\\n"
            + "|  __  | / /\\ \\ |  __  | / /\\ \\\n"
            + "| |  | |/ ____ \\| |  | |/ ____ \\\n"
            + "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";
    private static final String STARTER = "Dude, I'm HAHA.\n"
            + "What can I do for you?\n"
            + "Hint: deadline & event to include date & time\n"
            + "Sample: deadline homework /by 2020-02-02 18:00\n"
            + "(Oh when you are done, say bye)\n";

    private static final String TASK_NOT_FOUND = "OH WAIT A MINUTE!\n"
            + ":(\n"
            + "No related tasks in your list!";

    /**
     * Returns warning about a task that cannot be located.
     */
    public String taskNotFound() {
        System.out.println(TASK_NOT_FOUND);
        return TASK_NOT_FOUND;
    }

    /**
     * Outputs welcome message.
     */
    public String welcome() {
        System.out.println(STARTER);
        return STARTER;
    }

    /**
     * Outputs goodbye message.
     */
    public String bye() {
        System.out.println("Bye now!");
        return "Bye now!";
    }
}
