package duke;

import duke.commands.*;

import java.time.LocalDateTime;

public class Parser {
    private static boolean checkMatchString(String line, String match) {
        return line.length() >= match.length() && line.startsWith(match);
    }

    public static Command parse(String line) throws DukeException {
        if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (checkMatchString(line, "done ")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "I'm sorry, but done needs the index of a Task.");
            int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
            return new DoneCommand(index);
        } else if (checkMatchString(line, "todo ")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of a todo cannot be empty.");
            String taskName = cmdArgs[1];
            return new TodoCommand(taskName);
        } else if (checkMatchString(line, "deadline ")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of a todo cannot be empty.");
            String[] deadlineArgs = cmdArgs[1].split(" /by ", 2);
            if (deadlineArgs.length < 2) {
                throw new DukeException("The deadline needs to have a date specified with \"/by\".");
            }
            String taskName = deadlineArgs[0];
            LocalDateTime deadline = ParserUtils.parseDateTime(deadlineArgs[1], "The deadline needs to be specified in a valid date format.");
            return new DeadlineCommand(taskName, deadline);
        } else if (checkMatchString(line, "event ")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "The description of an event cannot be empty.");
            String[] eventArgs = cmdArgs[1].split(" /at ", 2);
            if (eventArgs.length < 2) {
                throw new DukeException("The event needs to have a date specified with \"/at\".");
            }
            String taskName = eventArgs[0];
            LocalDateTime datetime = ParserUtils.parseDateTime(eventArgs[1], "The event date needs to be specified in a valid date format.");
            return new EventCommand(taskName, datetime);
        } else if (checkMatchString(line, "delete ")) {
            String[] cmdArgs = ParserUtils.getCommandArgs(line, "I'm sorry, but delete needs the index of a Task.");
            int index = ParserUtils.parseInt(cmdArgs[1], "The index of the task needs to be an integer.");
            return new DeleteCommand(index);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

    }
}
