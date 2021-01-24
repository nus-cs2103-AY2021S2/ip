package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;


public class Parser {

    public static Command parseCommand(String str, TaskList list) throws DukeException {
        int strLength = str.length();
        if (str.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (str.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (str.length() >= 9 && str.substring(0, 9).equalsIgnoreCase("deadline ")) {
            return new AddCommand(str, CommandType.Deadline);
        } else if (strLength >= 7 && str.substring(0, 7).equalsIgnoreCase("delete ")) {
            return new DeleteCommand(str);
        } else if (strLength >= 6 && str.substring(0, 6).equalsIgnoreCase("event ")) {
            return new AddCommand(str, CommandType.Event);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("todo ")) {
            return new AddCommand(str, CommandType.Todo);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("done ")) {
            return new DoneCommand(str);
        } else {
            throw new DukeException("Invalid command");
        }
    }
}
