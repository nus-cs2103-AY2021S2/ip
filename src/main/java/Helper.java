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

    public static <T> int arrayIndexOf(T[] arr, T value) {
        return IntStream.range(0, arr.length).filter(i -> arr[i].equals(value)).findFirst().orElse(-1);
    }
}
