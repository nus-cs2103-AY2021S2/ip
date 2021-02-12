package duke;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;

/**
 * Parser class to parse commands given by the user.
 */
public class Parser {
    private static boolean isCommandMatch(String line, String match) {
        return line.startsWith(match)
                && (line.length() == match.length()
                || line.charAt(line.indexOf(match) + match.length()) == ' ');
    }

    /**
     * Converts the user's input into a command object.
     * The command object would contain the code to carry out the relevant actions intended by the user.
     * @param line user's input
     * @return relevant command object
     * @throws DukeException
     */
    public static Command parse(String line) throws DukeException {
        if (line.equals("bye")) {
            return ExitCommand.buildInstance();
        } else if (line.equals("list")) {
            return ListCommand.buildInstance();
        } else if (isCommandMatch(line, "done")) {
            return DoneCommand.buildInstance(line);
        } else if (isCommandMatch(line, "todo")) {
            return TodoCommand.buildInstance(line);
        } else if (isCommandMatch(line, "deadline")) {
            return DeadlineCommand.buildInstance(line);
        } else if (isCommandMatch(line, "event")) {
            return EventCommand.buildInstance(line);
        } else if (isCommandMatch(line, "delete")) {
            return DeleteCommand.buildInstance(line);
        } else if (isCommandMatch(line, "find")) {
            return FindCommand.buildInstance(line);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
