package tlylt.haha;

/**
 * Representation of all user interaction.
 */
public class Ui {
    private static final String LINE_BREAK =
            "____________________________________________________________\n";
    private static final String LOGO = " _    _          _    _\n" +
            "| |  | |   /\\   | |  | |   /\\\n" +
            "| |__| |  /  \\  | |__| |  /  \\\n" +
            "|  __  | / /\\ \\ |  __  | / /\\ \\\n" +
            "| |  | |/ ____ \\| |  | |/ ____ \\\n" +
            "|_|  |_/_/    \\_\\_|  |_/_/    \\_\\\n";
    private static final String STARTER = "Hello from\n" + LOGO
            + LINE_BREAK
            + "Dude, I'm HAHA.\n"
            + "What can I do for you?\n"
            + "Hint: deadline & event to include date & time\n"
            + "e.g. 2020-02-02 18:00\n"
            + "(Oh when you are done, say bye)\n"
            + LINE_BREAK;

    /**
     * Outputs welcome message.
     */
    public void welcome() {
        System.out.println(STARTER);
    }

    /**
     * Outputs linebreak.
     */
    public void lineBreak() {
        System.out.println(LINE_BREAK);
    }

    /**
     * Outputs goodbye message.
     */
    public void bye() {
        System.out.println("Bye now!");
    }
}
