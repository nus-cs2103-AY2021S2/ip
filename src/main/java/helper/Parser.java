package helper;

import helper.command.*;

/**
 * Parsing class
 */
public class Parser {
    /**
     * Parses the user input for duke
     * @param s User input
     * @return Command to execute
     * @throws DukeException
     */
    public Command parse(String s) throws DukeException {
        String[] strings = s.split(" ", 2);
        String firstWord = strings[0];
        try {
            switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(strings[1]);
            case "delete":
                return new DeleteCommand(strings[1]);
            case "todo":
                return new AddCommand(firstWord, strings[1]);
            case "event":
                return new AddCommand(firstWord, strings[1]);
            case "deadline":
                return new AddCommand(firstWord, strings[1]);
            case "find":
                return new FindCommand(strings[1]);
            default:
                throw new DukeException("Weird keyword...");
            }
        } catch (Exception e) {
            throw new DukeException("Invalid Command");
        }
    }

}
