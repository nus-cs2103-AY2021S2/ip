/**
 * Ui class for CS2103T iP. Handles printing of messages from the program.
 */

public class Ui {
    private final String LINES = "    ____________________________________________________________";
    private final String INDENTATION = "     ";

    /**
     * Prints supplied message within the 2 lines.
     * @param text Message to be printed.
     */
    public void print(String text) {
        System.out.println(LINES);
        System.out.print(INDENTATION);
        System.out.println(text);
        System.out.println(LINES);
    }

    /**
     * Overloaded method for multiple line messages.
     * @param texts Multi line message stored in a String array.
     */
    public void print(String[] texts) {
        System.out.println(LINES);
        for (String text : texts) {
            System.out.print(INDENTATION);
            System.out.println(text);
        }
        System.out.println(LINES);
    }

    /**
     * Prints error messages.
     * @param err Error to be printed.
     */
    public void printErr(String err) {
        System.out.println(LINES);
        System.out.print(INDENTATION);
        System.out.println("☹ OOPS!!! " + err);
        System.out.println(LINES);
    }

    /**
     * Prints the welcome logo and text.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINES);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(LINES);
    }

    /**
     * Prints upon program termination.
     */
    public void bye() {
        System.out.println(LINES);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(LINES);
    }

    /**
     * Prints when there is an IOException.
     */
    public void ioException() {
        System.out.println("☹ OOPS!!! An uncorrectable error occurred");
    }
}