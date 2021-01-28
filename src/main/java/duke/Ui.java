package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "\t--------------------------------\n";

    /**
     * Prints out the message in the duke.Duke user interface format.
     *
     * @param str the string to be printed out
     */
    public static void printFormatMessage(String str) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(str);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Prints out welcome message in the beginning.
     */
    public static void welcome() {
        printFormatMessage("\t  Hello! I'm duke.Duke\n" + "\t  What can I do for you?");
    }

    /**
     * Prints out welfare message in the end.
     */
    public static void welfare() {
        printFormatMessage("\t  Bye. Hope to see you again soon!");
    }


}
