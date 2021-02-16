package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.CommandWord;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class Parser
 * @author Png Zheng Jie, Sebastian
 * Description: Parses the user inputs into executable commands
 */
public class Parser {
    /**
     * parse: parses the user inputs into executable commands
     * @param userInput input string by user
     * @return an executable command
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        assert userInput != null : "user input should not be null";
        Pattern BASE_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<commandDescription>.*)");
        Matcher matcher = BASE_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        try {
            CommandWord commandWord = CommandWord.valueOf(matcher.group("commandWord").toUpperCase());
            String commandDescription = matcher.group("commandDescription").trim();

            switch (commandWord) {
            case DEADLINE:
            case EVENT:
                return taskWithDateHandler(commandWord, commandDescription);

            case DELETE:
            case DONE:
                return commandWithIndexHandler(commandWord, commandDescription);

            default:
                return emptyCommandDescriptionHandler(commandWord, commandDescription);
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }

    /**
     * emptyCommandDescriptionHandler: handles cases when the command description is empty
     * @param commandWord
     * @param commandDescription
     * @return an executable command
     * @throws IncompleteInputException
     */
    private static Command emptyCommandDescriptionHandler(CommandWord commandWord, String commandDescription)
            throws IncompleteInputException {
        if (commandDescription.isEmpty()) {
            switch(commandWord) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            default:
                throw new IncompleteInputException(commandWord);
            }
        } else if (commandWord.equals(CommandWord.FIND)) {
            return new FindCommand(commandDescription);
        } else if (commandWord.equals(CommandWord.TODO)) {
            return new ToDoCommand(commandDescription);
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * taskWithDateHandler: handles deadline and event commands
     * @param commandWord
     * @param commandDescription
     * @return an executable command
     * @throws DukeException
     */
    private static Command taskWithDateHandler(CommandWord commandWord, String commandDescription)
            throws DukeException {
        emptyCommandDescriptionHandler(commandWord, commandDescription);

        if (commandWord.equals(CommandWord.DEADLINE)) {
            Pattern DEADLINE_FORMAT = Pattern.compile("(?<deadlineDescription>.*) /by (?<deadlineDate>.*)");
            Matcher deadlineMatcher = DEADLINE_FORMAT.matcher(commandDescription);

            if (!deadlineMatcher.matches()) {
                throw new InvalidInputFormatException(commandWord);
            }
            return new DeadlineCommand(deadlineMatcher.group("deadlineDescription"),
                    deadlineMatcher.group("deadlineDate"));
        } else if (commandWord.equals(CommandWord.EVENT)) {
            Pattern EVENT_FORMAT = Pattern.compile("(?<eventDescription>.*) /at (?<eventDate>.*)");
            Matcher eventMatcher = EVENT_FORMAT.matcher(commandDescription);

            if (!eventMatcher.matches()) {
                throw new InvalidInputFormatException(commandWord);
            }
            return new EventCommand(eventMatcher.group("eventDescription"),
                    eventMatcher.group("eventDate"));
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * commandWithIndexHandler: handles delete and done commands
     * @param commandWord
     * @param commandDescription
     * @return an executable command
     * @throws DukeException
     */
    private static Command commandWithIndexHandler(CommandWord commandWord, String commandDescription)
            throws DukeException {
        try {
            emptyCommandDescriptionHandler(commandWord, commandDescription);
            int index = Integer.parseInt(commandDescription);

            if (commandWord.equals(CommandWord.DELETE)) {
                return new DeleteCommand(index);
            } else if (commandWord.equals(CommandWord.DONE)) {
                return new DoneCommand(index);
            } else {
                return new InvalidCommand();
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException(commandWord);
        }
    }
}
