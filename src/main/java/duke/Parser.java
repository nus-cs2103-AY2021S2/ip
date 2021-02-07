package duke;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TaskdateCommand;
import duke.command.FindCommand;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents a parser that makes sense of user's commands.
 */
public class Parser {

    /**
     * Parses through user command and calls the relevant command.
     *
     * @param fullCommand User command.
     * @return Relevant command.
     * @throws DukeException If user input is not in the correct format or is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String type = fullCommand.split(" ")[0];

        String description;

        if (type.equals("list")) {
            return new ListCommand();
        } else if (type.equals("bye")) {
            return new ExitCommand();
        } else if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            if (fullCommand.split(" ").length == 1) {
                String aOrAn = (type.equals("event")) ? "an " : "a ";
                throw new DukeException("☹ OOPS!!! The description of " + aOrAn + type + " cannot be empty.");
            }
            description = fullCommand.split(type + " ")[1];
            return new AddCommand(type, description);
        } else if (type.equals("delete")) {
            if (fullCommand.split(" ").length == 1) {
                throw new DukeException("☹ OOPS!!! The task number has not been specified.");
            }
            description = fullCommand.split(type + " ")[1];
            return new DeleteCommand(description);
        } else if (type.equals("done")) {
            if (fullCommand.split(" ").length == 1) {
                throw new DukeException("☹ OOPS!!! The task number has not been specified.");
            }
            description = fullCommand.split(type + " ")[1];
            return new DoneCommand(description);
        } else if (type.equals("taskdate")) {
            description = fullCommand.split(type + " ")[1];
            return new TaskdateCommand(description);
        } else if (type.equals("find")) {
            description = fullCommand.split(type + " ")[1];
            return new FindCommand(description);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

