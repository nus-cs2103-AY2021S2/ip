package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SetCommand;
import duke.command.TodoCommand;
import duke.exception.CommandNotFoundException;
import duke.exception.DescriptionMissingException;
import duke.exception.DukeException;
import duke.task.EnumTask;
import duke.task.TaskDescription;

/**
 * A class represents a CommandParser.
 */
public class CommandParser implements Parser {
    /**
     * Returns a Command based on user's input.
     * @param input The full command from user's input.
     * @return A command base on the input.
     * @throws DukeException If error occurs during the process.
     */
    public static Command parseCommand(String input) throws DukeException {
        String cleanerInput = input.trim();
        int endIndex = cleanerInput.indexOf(" ");
        String potentialCommand = (endIndex == -1) ? cleanerInput : cleanerInput.substring(0, endIndex);
        Command command;

        if (potentialCommand.length() == 0) {
            throw new DescriptionMissingException("Please enter something!");
        }

        assert(potentialCommand.length() > 0);
        if (potentialCommand.equalsIgnoreCase("LIST")) {
            command = new ListCommand();
        } else if (potentialCommand.equalsIgnoreCase("BYE")) {
            command = new ExitCommand();
        } else if (potentialCommand.equalsIgnoreCase("DONE")) {
            command = new DoneCommand(IndexParser.parseIndex(cleanerInput, endIndex));
        } else if (potentialCommand.equalsIgnoreCase("DELETE")) {
            command = new DeleteCommand(IndexParser.parseIndex(cleanerInput, endIndex));
        } else if (potentialCommand.equalsIgnoreCase("SET")) {
            command = new SetCommand(IndexParser.parseIndex(cleanerInput, endIndex),
                    IndexParser.parsePriority(cleanerInput));
        } else if (potentialCommand.equalsIgnoreCase("TODO")) {
            TaskDescription td = DescriptionParser.parseDescription(EnumTask.TODO, cleanerInput, endIndex);
            command = new TodoCommand(td);
        } else if (potentialCommand.equalsIgnoreCase("DEADLINE")) {
            TaskDescription td = DescriptionParser.parseDescription(EnumTask.DEADLINE, cleanerInput, endIndex);
            command = new DeadlineCommand(td);
        } else if (potentialCommand.equalsIgnoreCase("EVENT")) {
            TaskDescription td = DescriptionParser.parseDescription(EnumTask.EVENT, cleanerInput, endIndex);
            command = new EventCommand(td);
        } else if (potentialCommand.equalsIgnoreCase("FIND")) {
            command = new FindCommand(KeywordParser.parseKeyword(cleanerInput, endIndex));
        } else {
            throw new CommandNotFoundException("What do you mean? I do not know this command.");
        }
        return command;
    }
}
