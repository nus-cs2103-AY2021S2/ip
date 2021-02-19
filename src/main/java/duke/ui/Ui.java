package duke.ui;

/**
 * Ui allows interactions with the user and formats Duke's responses to the user.
 */
public class Ui {
    private boolean exit;

    /**
     * Constructs a Ui.
     */
    public Ui() {
        this.exit = false;
    }

    /**
     * Greets the user.
     *
     * @return A String representing greetings to the user.
     */
    public String greet() {
        String logo = " ____         _        \n"
                    + "|  _ \\ _   _| |  _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        greeting += "_____________________________________\n";
        greeting += "Hello! I'm Duke\n";
        greeting += "What can I do for you?\n";
        greeting += "______________________________________\n";
        return greeting;
    }

    /**
     * Formats a given string (from either the user or from Duke)
     *
     * @param response A String to be formatted.
     * @return Formatted input String.
     */
    public String format(String response) {
        String result = "_______________________________________________\n";
        result += response + "\n";
        result += "_______________________________________________\n";
        return result;
    }
}
