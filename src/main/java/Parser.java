import commands.*;
import exceptions.UnsupportedCommandException;

public class Parser {

    /**
     * Main driver for parsing any user input. The first word of user input contains the command name,
     * while any subsequent words contain arguments for the command.
     * @param userInput User input from terminal
     * @return parsed command
     */
    public Command parseInputLine(String userInput) throws UnsupportedCommandException {

        // SETTING UP THE VARIABLES NEEDED FOR ERROR CHECKING / PARSING
        int firstSpaceIndex = userInput.indexOf(" ");
        String firstWord;
        String commandBody;

        if (firstSpaceIndex == -1) {
            firstWord = userInput.trim();
            commandBody = "";
        } else {
            firstWord = userInput.substring(0, firstSpaceIndex);
            commandBody = userInput.substring(firstSpaceIndex).trim();
        }

        return parseIntoCommand(firstWord, commandBody);
    }

    /**
     * Calls the appropriate command constructor based on the first word in the user input
     * @param firstWord Corresponds to a command name or a short-form
     * @param commandBody Arguments provided for the command
     * @return A command that can be run
     * @throws UnsupportedCommandException
     */
    private Command parseIntoCommand(String firstWord, String commandBody) throws UnsupportedCommandException {
        switch (firstWord) {
        case "list":
        case "ls":
        case "l":
            return new ListCommand(commandBody);
        case "bye":
            return new ByeCommand(commandBody);
        case "todo":
        case "t":
            return new TodoCommand(commandBody);
        case "event":
        case "e":
            return new EventCommand(commandBody);
        case "deadline":
        case "dl":
            return new DeadlineCommand(commandBody);
        case "done":
            return new DoneCommand(commandBody);
        case "delete":
        case "del":
            return new DeleteCommand(commandBody);
        case "find":
            return new FindCommand(commandBody);
        case "h":
        case "help":
            return new HelpCommand(commandBody);
        default:
            throw new UnsupportedCommandException();
        }
    }
}
