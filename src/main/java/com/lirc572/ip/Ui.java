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
    public static String printLine(String line) {
        return line + "\n";
    }

    /**
     * Prints a horizontal line.
     */
    public static String printHorizontalLine() {
        return "________________________________________\n";
    }

    /**
     * Prints an empty line.
     */
    public static String printEmptyLine() {
        return "\n";
    }

    /**
     * Prints the Duke logo.
     */
    public static String printLogo() {
        String res = "";
        for (String line : logo.split("\n")) {
            res += printLine(line);
        }
        return res;
    }

    /**
     * Prints the specified error message.
     *
     * @param errorMessage The error message to print.
     */
    public static String printError(String errorMessage) {
        return printLine("Error: " + errorMessage);
    }

    /**
     * Prints the error message of the specified Exception.
     *
     * @param e The Exception whose error message is to be printed.
     */
    public static String printError(Exception e) {
        return printError(e.getMessage());
    }
}
