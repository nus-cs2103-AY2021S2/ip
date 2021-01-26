package duke;

import duke.command.*;

public class Parser {

    Parser() {}

    public static Command parse(String command) throws DukeException {
        String strippedCommand = command.strip();
        Command result = null;
        if (strippedCommand.equals("bye")) {
            result = new ByeCommand("");
        } else if (strippedCommand.equals("list")) {
            result = new ListCommand("");
        } else if (strippedCommand.startsWith("done")) {
            String args = strippedCommand.substring(4).strip();
            result = new DoneCommand(args);
        } else if (strippedCommand.startsWith("delete")) {
            String args = strippedCommand.substring(6).strip();
            result = new DeleteCommand(args);
        } else if (strippedCommand.startsWith("todo")) {
            String args = strippedCommand.substring(4).strip();
            result = new TodoCommand(args);
        } else if (strippedCommand.startsWith("deadline")) {
            String args = strippedCommand.substring(8).strip();
            result = new DeadlineCommand(args);
        } else if (strippedCommand.startsWith("event")) {
            String args = strippedCommand.substring(5).strip();
            result = new EventCommand(args);
        } else {
            String msg = "I apologize, I do not comprehend your command.";
            DukeException exception = new DukeException(msg);
            throw exception;
        }
        return result;
    }
}
