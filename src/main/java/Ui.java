/**
 * Ui class for CS2103T iP. Handles printing of messages from the program.
 */

public class Ui {
    private final String lines;
    private final String indentation;

    /**
     * Creates Ui object. Saves lines and indentation for future messages.
     */
    public Ui() {
        this.lines = "    ____________________________________________________________";
        this.indentation = "     ";
    }

    /**
     * Prints supplied message within the 2 lines.
     * @param text Message to be printed.
     */
    public void print(String text) {
        System.out.println(lines);
        System.out.print(indentation);
        System.out.println(text);
        System.out.println(lines);
    }

    /**
     * Overloaded method for multiple line messages.
     * @param texts Multi line message stored in a String array.
     */
    public void print(String[] texts) {
        System.out.println(lines);
        for (String text : texts) {
            System.out.print(indentation);
            System.out.println(text);
        }
        System.out.println(lines);
    }

    /**
     * Prints error messages.
     * @param err Error to be printed.
     */
    public void printErr(String err) {
        System.out.println(lines);
        System.out.print(indentation);
        System.out.println("☹ OOPS!!! " + err);
        System.out.println(lines);
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
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(lines);
    }

    /**
     * Prints upon program termination.
     */
    public void bye() {
        System.out.println(lines);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(lines);
    }

    /**
     * Prints when there is an IOException.
     */
    public void ioException() {
        System.out.println("☹ OOPS!!! An uncorrectable error occurred");
    }
}