package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.DukeException;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;

/**
 * Parses Duke commands.
 *
 * @author Benedict Khoo
 */
public class Parser {
    /**
     * Parses the raw input string and returns an appropriate command. If the command is invalid, an InvalidCommand
     * is returned.
     *
     * @param rawInput The string of user input.
     * @return The relevant command.
     */
    public Command parseCmd(String rawInput) {
        assert rawInput != null : "rawInput must not be null!";
        String cmd = rawInput.trim();

        try {
            if (ExitCommand.isExitCommand(cmd)) {
                return new ExitCommand();
            } else if (AddToDoCommand.isAddToDoCommand(cmd)) {
                return AddToDoCommand.parseAddToDoCommand(cmd);
            } else if (AddDeadlineCommand.isAddDeadlineCommand(cmd)) {
                return AddDeadlineCommand.parseAddDeadlineCommand(cmd);
            } else if (AddEventCommand.isAddEventCommand(cmd)) {
                return AddEventCommand.parseAddEventCommand(cmd);
            } else if (ListCommand.isListCommand(cmd)) {
                return ListCommand.parseListCommand(cmd);
            } else if (DoneCommand.isDoneCommand(cmd)) {
                return DoneCommand.parseDoneCommand(cmd);
            } else if (DeleteCommand.isDeleteCommand(cmd)) {
                return DeleteCommand.parseDeleteCommand(cmd);
            } else if (FindCommand.isFindCommand(cmd)) {
                return FindCommand.parseFindCommand(cmd);
            } else {
                return new InvalidCommand(String.format("Sorry, I don't know what '%s' means", cmd));
            }
        } catch (DukeException ex) {
            return new InvalidCommand(ex.getMessage());
        }
    }
}
