package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Public interface to provide convenient text formatting.
 */
public interface Formatter {
    /**
     * Wraps text in command-line style dashed lines before and after the text.
     *
     * @param output The text to be formatted.
     * @return Formatted text.
     */
    static String formatOut(String output) {
        String opening = "\"----------------------------------------\n";
        String closing = "----------------------------------------\"\n";
        String combined = opening + output + "\n" + closing;
        return addIndent(combined);
    }

    /**
     * Adds numbering to each string in the strArray and returns a single String.
     *
     * @param strArray Array of Strings to be formatted as a single list.
     * @return String with new line chars to represent the list.
     */
    static String formatList(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = (i + 1) + ". " + strArray[i];
        }
        return String.join("\n", strArray);
    }

    static String formatList(List<String> strList) {
        String[] strArr = strList.toArray(new String[]{});
        return formatList(strArr);
    }

    /**
     * Prefixes a tab character to every new line in the input string, providing
     * indentation to the whole string.
     *
     * @param string String to be formatted.
     * @return Indented String.
     */
    static String addIndent(String string) {
        String[] strings = string.split("\n");
        return Arrays.stream(strings)
                .map(s -> '\t' + s)
                .collect(Collectors.joining("\n"));
    }
}
