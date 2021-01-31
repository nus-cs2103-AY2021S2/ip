package duke.parser;

import duke.exceptions.UnknownCommandException;
import duke.utils.Command;

/**
 * Parse input.
 */
public class Parser {
    /**
     * Splits input into substrings.
     * @param input User input.
     * @return String[] array of substrings.
     */
    public static String[] splitIntoSubstrings(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parse user input.
     * @param subStrings substrings of user input.
     * @return Command that has been parsed.
     * @throws UnknownCommandException If input cannot be parsed.
     */
    public static Command parseCommand(String[] subStrings) throws UnknownCommandException {
        try {
            return Command.valueOf(subStrings[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(subStrings[0].trim());
        }
    }
}
