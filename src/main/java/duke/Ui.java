package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the user command.
     * @return The full user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints greeting message when users start Duke.
     */
    public void printGreetingMsg() {
        printLine();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printMsg("Hello! I'm duke.Duke");
        printMsg("What can I do for you?");
        printLine();
    }

    /**
     * Prints messages with proper indentations.
     * @param msg The message that will be printed on the screen and visible to users.
     */
    public void printMsg(String msg) {
        System.out.println("     " + msg);
    }

    /**
     * Prints a line with proper indentations.
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints bye message when users quiting Duke.
     */
    public void sayBye() {
        printMsg("Bye. Hope to see you again soon!");
    }
}
