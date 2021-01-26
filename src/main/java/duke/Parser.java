package duke;

import duke.command.Command;

public class Parser {

    Parser() {}

    public Command parseCommand(String command) throws DukeException {
        String strippedCommand = command.strip();
        Command result = null;
        if (strippedCommand.equals("list")) {
            result = new Command(Com.LIST, "");
        } else if (strippedCommand.startsWith("done")) {
            String args = strippedCommand.substring(4).strip();
            result = new Command(Com.DONE, args);
        } else if (strippedCommand.startsWith("delete")) {
            String args = strippedCommand.substring(6).strip();
            result = new Command(Com.DELETE, args);
        } else if (strippedCommand.startsWith("todo")) {
            String args = strippedCommand.substring(4).strip();
            result = new Command(Com.TODO, args);
        } else if (strippedCommand.startsWith("deadline")) {
            String args = strippedCommand.substring(8).strip();
            result = new Command(Com.DEADLINE, args);
        } else if (strippedCommand.startsWith("event")) {
            String args = strippedCommand.substring(5).strip();
            result = new Command(Com.EVENT, args);
        } else {
            String msg = "I apologize, I do not comprehend your command.";
            DukeException exception = new DukeException(msg);
            throw exception;
        }
        return result;
    }
}
