package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }
    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadFileError() {
        printIndented("Unable to load task list from file. A new task list is created.");
    }

    public void showError(DukeException e) {
        printIndented(e.toString());
    }

    public void printLineBreak() {
        System.out.println("    ____________________________________________________________");
    }

    public void printIndented(String text) {
        System.out.println(String.format("        %s", text));
    }

    public void greeting() {
        printLineBreak();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLineBreak();
    }
}
