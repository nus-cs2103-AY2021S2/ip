public class Ui {

    private final String DUKE_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _ ___\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String WELCOME_MESSAGE = "Hello! I'm Duke.\n"
            + "What can I do for you today?";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final String REPLY_TOP_OUTLINE = "========================================";
    private final String REPLY_BOTTOM_OUTLINE = "========================================\n";

    public Ui() {
    }

    public void greet() {
        System.out.println(DUKE_LOGO);
        echo(WELCOME_MESSAGE);
    }

    public void print(String output) {
        echo(output);
    }

    private void echo(String output) {
        System.out.println(REPLY_TOP_OUTLINE);
        System.out.println(output);
        System.out.println(REPLY_BOTTOM_OUTLINE);
    }
}
