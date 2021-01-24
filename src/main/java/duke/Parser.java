package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;

/**
 * A class that identify the keyword of given string.
 */
public class Parser {

    /**
     * Returns the command according to the first word.
     * Arguments are not parsed in this method.
     *
     * @param str Input string.
     * @param list TaskList that stores commands.
     * @return Commands.
     * @throws DukeException If the first word is not any known command type.
     */
    public static Command parseCommand(String str, TaskList list) throws DukeException {
        int strLength = str.length();
        if (str.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (str.equalsIgnoreCase("list")) {
            return new ListCommand(list);
        } else if (str.length() >= 9 && str.substring(0, 9).equalsIgnoreCase("deadline ")) {
            return new AddCommand(str, CommandType.DEADLINE);
        } else if (strLength >= 7 && str.substring(0, 7).equalsIgnoreCase("delete ")) {
            return new DeleteCommand(str);
        } else if (strLength >= 6 && str.substring(0, 6).equalsIgnoreCase("event ")) {
            return new AddCommand(str, CommandType.EVENT);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("todo ")) {
            return new AddCommand(str, CommandType.TODO);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("done ")) {
            return new DoneCommand(str);
        } else {
            throw new DukeException("Invalid command");
        }
    }
}
