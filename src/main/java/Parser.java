/**
 * Parser class used to parse input into appropriate formats.
 */
class Parser {
    /**
     * Parse given line into an array where the first element is a keyword.
     *
     * @param string Input Line.
     */
    public static String[] parse(String string) {
        String[] inputArray = string.split(" ", 2);
        return inputArray;
    }
}
