package duke.parser;

import duke.exception.DescriptionMissingException;

/**
 * A class represents an IndexParser.
 */
public class IndexParser implements Parser {
    /**
     * Return the index contained in the input string.
     * @param input The input string given by user.
     * @return The index contained in the input string.
     * @throws DescriptionMissingException If the index is not in the input string.
     */
    public static int parseIndex(String input) throws DescriptionMissingException {
        String[] doneInstructions = input.trim().split(" ");

        if (doneInstructions.length < 2) {
            throw new DescriptionMissingException("Please specify the index!");
        }
        try {
            String indexInString = doneInstructions[1].trim();
            return Integer.parseInt(indexInString) - 1;
        } catch (NumberFormatException e) {
            throw new DescriptionMissingException("Input index is not valid");
        }
    }
}
