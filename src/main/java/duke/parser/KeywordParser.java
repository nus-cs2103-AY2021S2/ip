package duke.parser;

import duke.exception.DescriptionMissingException;

/**
 * A class that represents a KeywordParser.
 */
public class KeywordParser implements Parser {
    /**
     * Returns the keyword from the user's input.
     * @param input The input string given by user.
     * @param index The index at the end of Find command.
     * @return The keyword contained in the input string.
     * @throws DescriptionMissingException If the keyword is not included in the input string.
     */
    public static String parseKeyword(String input, int index) throws DescriptionMissingException {
        if (index == -1) {
            throw new DescriptionMissingException("Please include the keyword!");
        }
        return input.substring(index).trim();
    }
}
