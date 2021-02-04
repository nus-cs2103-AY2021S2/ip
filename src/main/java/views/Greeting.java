package views;

public class Greeting {
    private static final String divider = "---";

    private void printWithSpacing(String text) {
        System.out.println(String.format("\n%s\n%s\n%s\n", divider, text, divider));
    }

    /**
     * Outputs the standard greeting with Duke Logo
     */
    public void greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Outputs the standard bye greeting for a user
     */
    public void bye() {
        printWithSpacing("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message from exception
     * 
     * @param exception exception caught during runtime in Ui
     */
    public void printErrorMessage(Exception exception) {
        printWithSpacing(exception.getMessage());
    }
}
