package duke;

import java.util.List;

public class Ui {

    /**
     * Prints each string in output array in a new line, using duke's style.
     * @param output array of strings to be printed out.
     */
    public static void printWithStyle(String[] output) {
        System.out.println("    ________________________________________________________________");
        for (String str : output) {
            System.out.println("    " + str);
        }
        System.out.println("    ________________________________________________________________");
    }

    /**
     * Prints each string in output List in a new line, using duke's style.
     * @param output list of strings to be printed out.
     */
    public static void printWithStyle(List<String> output) {
        System.out.println("    ________________________________________________________________");
        for (String str : output) {
            System.out.println("    " + str);
        }
        System.out.println("    ________________________________________________________________");
    }

    /**
     * Prints the output string using duke's style.
     * @param output string to be printed out.
     */
    public static void printWithStyle(String output) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + output);
        System.out.println("    ________________________________________________________________");
    }
}
