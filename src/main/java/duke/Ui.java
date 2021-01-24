package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    static String horizontalLine = "\t--------------------------------\n";

    /**
     * Prints out the message in the Duke user interface format.
     *
     * @param str the string to be printed out
     */
    public void printFormatMessage(String str) {
        System.out.print(Ui.horizontalLine);
        System.out.println(str);
        System.out.print(Ui.horizontalLine);
    }

    /**
     * Prints out welcome message in the beginning.
     */
    public void welcome() {
        printFormatMessage("\t  Hello! I'm Duke\n" + "\t  What can I do for you?");
    }

    /**
     * Prints out welfare message in the end.
     */
    public void welfare() {
        printFormatMessage("\t  Bye. Hope to see you again soon!");
    }


}
