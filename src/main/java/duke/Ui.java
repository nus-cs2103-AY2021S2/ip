package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints Text formatted between 2 lines.
     *
     * @param text Text to be printed.
     */
    public void sendToUser(String text) {
        String line = "    _______________________________________\n    ";
        System.out.println(line + text + "\n" + line);
    }

    public void showError() {
        System.out.println("Error.");
    }

    /**
     * Prints the exception.
     *
     * @param e The exception to be printed.
     */
    public void showError(Exception e) {
        System.out.println(e);
    }

    /**
     * Reads a line from stdin.
     *
     * @return String representing a command.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Returns the Scanner object.
     *
     * @return Scanner object
     */
    public Scanner getScanner() {
        return sc;
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm a bot called Duke. Beep boop. \nWhat do you want?\n");
    }

    /**
     * Prints a closing message.
     */
    public void bye() {
        this.sc.close();
        this.sendToUser("BYE AND HAVE A GOOD DAY. Beep boop.");
    }
}
