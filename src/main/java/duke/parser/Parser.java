package duke.parser;

import duke.exceptions.UnknownCommandException;
import duke.utils.Command;

public class Parser {
    public static String[] splitIntoTokens(String input) {
        return input.split(" ", 2);
    }

    public static Command parseCommand(String[] tokens) throws UnknownCommandException {
        if (tokens[0].isEmpty()) {
            return Command.SKIP;
        }

        try {
            return Command.valueOf(tokens[0].trim().toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException(tokens[0].trim());
        }
    }
}
