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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class Parser
 * @author Png Zheng Jie, Sebastian
 * Description: Parses the user inputs into executable commands
 */
public class Parser {
    private static final Pattern BASE_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<commandDescription>.*)");
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<deadlineDescription>.*) /by (?<deadlineDate>.*)");
    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<eventDescription>.*) /at (?<eventDate>.*)");

    /**
     * parse: parses the user inputs into executable commands
     * @param userInput input string by user
     * @return an executable command
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        Matcher matcher = BASE_COMMAND_FORMAT.matcher(userInput.trim());
        CommandWord commandWord;
        String commandDescription;

        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        try {
            commandWord = CommandWord.valueOf(matcher.group("commandWord").toUpperCase());
            commandDescription = matcher.group("commandDescription").trim();
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }

        switch (commandWord) {
        case BYE:
            if (commandDescription.isEmpty()) {
                return new ByeCommand();
            }
            break;

        case DEADLINE:
            if (commandDescription.isEmpty()) {
                throw new DukeException("OOPS!!! Deadline description cannot be empty!");
            }

            Matcher deadlineMatcher = DEADLINE_FORMAT.matcher(commandDescription);
            if (deadlineMatcher.matches()) {
                return new DeadlineCommand(deadlineMatcher.group("deadlineDescription"),
                        deadlineMatcher.group("deadlineDate"));
            }

            throw new DukeException("OOPS!!! Please specify deadline in this format:\n" +
                    "    deadline [description] /by [date]\n" + "(E.g. deadline project /by 2021-01-22");

        case DELETE:
            try {
                if (!commandDescription.isEmpty()) {
                    int index = Integer.parseInt(commandDescription);
                    return new DeleteCommand(index);
                }
                throw new DukeException("OOPS!!! Please specify a task number in this format:\n" +
                        "    delete [task number] (E.g. delete 2)");
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please specify a task number in this format:\n" +
                        "    delete [task number] (E.g. delete 2)");
            }

        case DONE:
            try {
                if (!commandDescription.isEmpty()) {
                    int index = Integer.parseInt(commandDescription);
                    return new DoneCommand(index);
                }
                throw new DukeException("OOPS!!! Please specify a task number in this format:\n" +
                        "    done [task number] (E.g. done 2)");
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! Please specify a task number in this format:\n" +
                        "    done [task number] (E.g. done 2)");
            }

        case EVENT:
            if (commandDescription.isEmpty()) {
                throw new DukeException("OOPS!!! Event description cannot be empty!");
            }

            Matcher eventMatcher = EVENT_FORMAT.matcher(commandDescription);
            if (eventMatcher.matches()) {
                return new EventCommand(eventMatcher.group("eventDescription"),
                        eventMatcher.group("eventDate"));
            }

            throw new DukeException("OOPS!!! Please specify event in this format:\n" +
                    "    event [description] /at [date]\n" + "(E.g. event wedding anniversary /at 2021-01-22");

        case FIND:
            if (!commandDescription.isEmpty()) {
                return new FindCommand(commandDescription);
            }
            throw new DukeException("OOPS!!! Please specify a keyword in this format:\n" +
            "    find [keyword] (E.g. find book)");

        case LIST:
            if (commandDescription.isEmpty()) {
                return new ListCommand();
            }
            break;

        case TODO:
            if (!commandDescription.isEmpty()) {
                return new ToDoCommand(commandDescription);
            }
            throw new DukeException("OOPS!!! ToDo description cannot be empty!");
        }

        return new InvalidCommand();
    }
}
