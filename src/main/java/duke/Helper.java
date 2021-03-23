package duke;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Contains helper functions that are used across several classes.
 */
public class Helper {
    /**
     * Joins a sub-array of strings into 1 string where each element in the sub-array is separated by a space.
     *
     * @param arr array containing sub-array to join
     * @param start start index of sub-array to join
     * @param end end index (inclusive) of sub-array to join
     * @return string of sub-array joined with space
     */
    public static String join(String[] arr, int start, int end) {
        StringBuilder outputBuilder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            outputBuilder.append(arr[i]);
            if (i < end) {
                outputBuilder.append(" ");
            }
        }
        return outputBuilder.toString();
    }

    /**
     * Joins a sub-array of strings into 1 string where each element in the sub-array is separated by a space.
     * The sub-array consists of every element from the provided start index to the end of the array.
     *
     * @param arr array containing sub-array to join
     * @param start start index of sub-array to join
     * @return string of sub-array joined with space
     */
    public static String join(String[] arr, int start) {
        return join(arr, start, arr.length - 1);
    }

    /**
     * Gets the index of a given value in a given array.
     * @param arr array to be searched.
     * @param value value to look for.
     * @param <T> type of the value and the type which the array contains.
     * @return the index of the value within the array.
     */
    public static <T> int arrayIndexOf(T[] arr, T value) {
        IntStream myStream = IntStream.range(0, arr.length);
        //Filters the stream to get indices of array which has elements equal to value that we are looking for.
        IntStream filteredStream = myStream.filter(i -> arr[i].equals(value));
        //First index in the stream.
        int firstIndex = filteredStream.findFirst().orElse(-1);
        return firstIndex;
    }

    /**
     * Concatenates the input strings with a new line between them, except the last string.
     * @param input an array of strings to be formatted.
     * @return the formatted string.
     */
    public static String formatStrings(String ... input) {
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
     * Concatenates the input strings from the List with a line separator between them.
     * @param input the List of Strings to be formatted.
     * @return the formatted String.
     */
    public static String formatStrings(List<String> input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            output.append(input.get(i));
            if (i != input.size() - 1) {
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }



    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    //@@author swayongshen-reused
    //Reused from https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java/5439547
    /**
     * Checks if a given string is an integer
     * @param s string to be checked.
     * @param radix the base of the number e.g. 10 for decimal integers.
     * @return true if the string is an integer.
     */
    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }
}
