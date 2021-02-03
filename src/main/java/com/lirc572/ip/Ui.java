package com.lirc572.ip;

/**
 * Contains static methods for drawing UI.
 */
public class Ui {

    /**
     * The logo of Duke.
     */
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    /**
     * Prints one line of string with spaces in front.
     *
     * @param line The line to print.
     */
    public static void printLine(String line) {
        System.out.println("     " + line);
    }

    /**
     * Prints a horizontal line.
     */
    public static void printHorizontalLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    /**
     * Prints an empty line.
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * Prints the Duke logo.
     */
    public static void printLogo() {
        for (String line : logo.split("\n")) {
            printLine(line);
        }
    }

    /**
     * Prints the specified error message.
     *
     * @param errorMessage The error message to print.
     */
    public static void printError(String errorMessage) {
        printLine("Error: " + errorMessage);
    }

    /**
     * Prints the error message of the specified Exception.
     *
     * @param e The Exception whose error message is to be printed.
     */
    public static void printError(Exception e) {
        printError(e.getMessage());
    }
}
