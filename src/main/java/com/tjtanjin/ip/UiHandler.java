package com.tjtanjin.ip;

import java.util.Scanner;

/**
 * Ui class handles direct interactions between the application and the user
 */
public class UiHandler {

    //color printing
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Greets the user upon program launch.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(ANSI_GREEN + "Hello from\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints line divider between commands for clarity.
     */
    public void showDivider() {
        System.out.println(ANSI_WHITE + "------------------------------------");
    }

    /**
     * Check response and print as info or error.
     * @param response error message to print
     */
    public void showResponse(String response) {
        if (response.startsWith("Error:")) {
            showError(response);
        } else {
            showInfo(response);
        }
    }

    /**
     * Prints information to the user
     * @param response information to print
     */
    public void showInfo(String response) {
        System.out.println(ANSI_GREEN + response.split(" ", 2)[1]);
    }

    /**
     * Prints error message to the yser
     * @param response error message to print
     */
    public void showError(String response) {
        System.out.println(ANSI_YELLOW + response);
    }

    /**
     * Terminates the program with a final message.
     * @param crashMsg message to print before termination
     */
    public static void terminate(String crashMsg) {
        System.out.println(ANSI_RED + crashMsg);
        System.exit(0);
    }

    /**
     * Read and return input from the user.
     * @param sc scanner to read next line
     * @return input from user
     */
    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }
}
