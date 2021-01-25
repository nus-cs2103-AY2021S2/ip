package com.tjtanjin.ip;

import java.util.Scanner;

/**
 * Ui class handles direct interactions between the application and the user
 */
public class Ui {

    /**
     * Greets the user upon program launch.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Prints line divider between commands for clarity.
     */
    public static void showDivider() {
        System.out.println("------------------------------------");
    }

    /**
     * Prints information to the user
     * @param info information to print
     */
    public static void showInfo(String info) {
        System.out.println(info);
    }

    /**
     * Prints error message to the yser
     * @param msg error message to print
     */
    public static void showError(String msg) {
        System.out.println("Error: " + msg);
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
