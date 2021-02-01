package duke;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Contains helper functions that are used across several classes.
 */
public class Helper {
    /**
     * Joins a sub-array of strings into 1 string where each element in sub-array is separate by a space.
     *
     * @param arr array containing sub-array to join
     * @param start start index of sub-array to join
     * @param end end index of sub-array to join
     * @return string of sub-array joined with space
     */
    public static String join(String[] arr, int start, int end) {
        StringBuilder output = new StringBuilder();
        for (int i = start; i <= end; i++) {
            output.append(arr[i]);
            if (i < end) {
                output.append(" ");
            }
        }
        return output.toString();
    }

    public static String join(String[] arr, int start) {
        return join(arr, start, arr.length - 1);
    }

    public static <T> int arrayIndexOf(T[] arr, T value) {
        return IntStream.range(0, arr.length).filter(i -> arr[i].equals(value)).findFirst().orElse(-1);
    }

    /**
     * Formats the array of input strings to an output string in which each input string is in a new line.
     */
    public static String formatStringArray(String[] input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            output.append(input[i]);
            if (i != input.length - 1) {
                output.append(System.lineSeparator());
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
