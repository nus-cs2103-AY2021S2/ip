package duke.parser;

import duke.exceptions.UnknownCommandException;
import duke.utils.Command;

/**
 * Parse input.
 */
public class Parser {
    /**
     * Splits input into substrings.
     *
     * @param input User input.
     * @return String[] array of substrings.
     */
    public static String[] splitIntoSubstrings(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parse user input.
     *
     * @param commandString user input.
     * @return Command that has been parsed.
     * @throws UnknownCommandException If input cannot be parsed.
     */
    public static Command parseCommand(String commandString) throws UnknownCommandException {
        try {
            return Command.valueOf(commandString.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(commandString.trim());
        }
    }
}
