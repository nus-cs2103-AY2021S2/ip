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

import java.time.LocalDateTime;

/**
 * Parser class to parse commands given by the user.
 */
public class Parser {
    private static boolean isCommandMatch(String line, String match) {
        return line.startsWith(match) && (line.length() == match.length() || line.charAt(line.indexOf(match) + match.length()) == ' ');
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
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (isCommandMatch(line, "done")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "I'm sorry, but done needs the index of a Task.");
            int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
            return new DoneCommand(index);
        } else if (isCommandMatch(line, "todo")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of a todo cannot be empty.");
            String taskName = cmdArgs[1];
            return new TodoCommand(taskName);
        } else if (isCommandMatch(line, "deadline")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of a todo cannot be empty.");
            String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
            if (deadlineArgs.length < 2) {
                throw new DukeException("The deadline needs to have a date specified with \"/by\".");
            }
            String taskName = deadlineArgs[0];
            LocalDateTime deadline = ParserUtils.parseDateTime(deadlineArgs[1], "The deadline needs to be specified in a valid date format.");
            return new DeadlineCommand(taskName, deadline);
        } else if (isCommandMatch(line, "event")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of an event cannot be empty.");
            String[] eventArgs = cmdArgs[1].split(" /at ", 2);
            if (eventArgs.length < 2) {
                throw new DukeException("The event needs to have a date specified with \"/at\".");
            }
            String taskName = eventArgs[0];
            LocalDateTime dateTime = ParserUtils.parseDateTime(eventArgs[1], "The event date needs to be specified in a valid date format.");
            return new EventCommand(taskName, dateTime);
        } else if (isCommandMatch(line, "delete")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "I'm sorry, but delete needs the index of a Task.");
            int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
            return new DeleteCommand(index);
        } else if (isCommandMatch(line, "find")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "I'm sorry, but find needs a keyword specified.");
            String keyword = cmdArgs[1];
            return new FindCommand(keyword);

        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }
}
