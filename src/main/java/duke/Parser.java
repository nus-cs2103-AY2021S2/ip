package duke;

import duke.command.*;

/**
 * Parser class allows program to parse user commands.
 */
public class Parser {
    Parser() {}

    /**
     * Primary parse method that takes in a String command and returns a Command object that represents it.
     * @param command User command to be parsed
     * @return Command object representing the given command
     * @throws DukeException if command given is not supported
     */
    public static Command parse(String command) throws DukeException {
        String strippedCmd = command.strip();
        Command result = null;
        if (strippedCmd.equals("bye")) {
            result = new ByeCommand("");
        } else if (strippedCmd.equals("list")) {
            result = new ListCommand("");
        } else if (strippedCmd.startsWith("done")) {
            String args = strippedCmd.substring(4).strip();
            result = new DoneCommand(args);
        } else if (strippedCmd.startsWith("delete")) {
            String args = strippedCmd.substring(6).strip();
            result = new DeleteCommand(args);
        } else if (strippedCmd.startsWith("todo")) {
            String args = strippedCmd.substring(4).strip();
            result = new TodoCommand(args);
        } else if (strippedCmd.startsWith("deadline")) {
            String args = strippedCmd.substring(8).strip();
            result = new DeadlineCommand(args);
        } else if (strippedCmd.startsWith("event")) {
            String args = strippedCmd.substring(5).strip();
            result = new EventCommand(args);
        } else {
            throw new DukeException("I apologize, I do not comprehend your command.");
        }
        return result;
    }
}
