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

    /**
     * Formats the array of input strings to an output string in which each input string is in a new line.
     */
    public static String formatStringArray(String[] input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            output.append(input[i]);
            if (i != input.length - 1) {
                output.append(System.getenv(System.lineSeparator()));
            }
        }
        return output.toString();
    }

    /**
     * Formats the List of input strings to an output string in which each input string is in a new line.
     */
    public static String formatStringArray(List<String> input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            output.append(input.get(i));
            if (i != input.size() - 1) {
                output.append(System.getenv(System.lineSeparator()));
            }
        }
        return output.toString();
    }

}
