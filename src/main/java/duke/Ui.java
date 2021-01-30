package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the command string input by user
     *
     * @return String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadFileError() {
        printIndented("Unable to load task list from file. A new task list is created.");
    }

    /**
     * Prints to console the DukeException
     *
     * @param e
     */
    public void showError(DukeException e) {
        printIndented(e.toString());
    }

    public void printLineBreak() {
        System.out.println("  ____________________________________________________________");
    }

    /**
     * Prints to console text with indentation
     *
     * @param text
     */
    public void printIndented(String text) {
        System.out.println(String.format("    %s", text));
    }

    /**
     * Prints to console greeting
     */
    public void greeting() {
        printLineBreak();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLineBreak();
    }
}
