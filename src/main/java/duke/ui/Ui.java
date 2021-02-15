package duke.ui;

import java.util.Scanner;

/**
 * Ui allows interactions with the user and prompts user for command
 */
public class Ui {
    private final Scanner sc;
    private boolean exit;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.exit = false;
    }

    public String greet() {
        String logo = " ____         _        \n"
                    + "|  _ \\ _   _| |  _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        greeting += "_____________________________________\n";
        greeting += "Hello! I'm Duke\n";
        greeting += "What can I do for you?\n";
        greeting += "______________________________________\n";
        return greeting;
    }

    public String format(String response) {
        String result = "_______________________________________________\n";
        result += response + "\n";
        result += "_______________________________________________\n";
        return result;
    }
}
