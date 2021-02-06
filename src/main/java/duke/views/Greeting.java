package duke.views;

public class Greeting {
    private static final String divider = "---";

    private static String stringWithSpacing(String text) {
        return String.format("\n%s\n%s\n%s\n", divider, text, divider);
    }

    /**
     * Outputs the standard greeting with duke.Duke Logo
     */
    public static String greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Outputs the standard bye greeting for a user
     */
    public static String bye() {
        return stringWithSpacing("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message from exception
     *
     * @param exception exception caught during runtime in Ui
     */
    public static String printErrorMessage(Exception exception) {
        return stringWithSpacing(exception.getMessage());
    }
}
