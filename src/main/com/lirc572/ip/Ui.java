package com.lirc572.ip;

public class Ui {
    private static final String logo = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|";

    /**
     * Print one line with spaces in front
     *
     * @param line the line to print
     */
    public static void printLine(String line) {
        System.out.println("     " + line);
    }

    /**
     * Print a horizontal line
     */
    public static void printHorizontalLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    /**
     * Print an empty line
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printLogo() {
        for (String line : logo.split("\n")) {
            printLine(line);
        }
    }

    public static void printError(String errorMessage) {
        printLine("Error: " + errorMessage);
    }

    public static void printError(Exception e) {
        printLine("Error: " + e.getMessage());
    }
}
