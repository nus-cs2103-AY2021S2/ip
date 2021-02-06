package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.showListCommand;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyLineException;
import duke.exceptions.IncorrectTypeException;

public class Parser {

    /**
     * Returns a command on what to do with the given description.
     *
     * @param input String of an input.
     * @return a new Command.
     * @throws DukeException If the input is blank.
     */
    public static Command parseTask(String input) throws DukeException {
        String[] descriptionArr = input.split(" ", 2); // split type of task from description
        String type = descriptionArr[0]; // type of task
        if (input.isBlank()) {
            throw new EmptyLineException(" ");
        }
        if (descriptionArr.length != 2) {
            descriptionArr = new String[]{type, ""};
        }
        switch (type) {
        case "list":
            return new showListCommand("list");
        case "done":
            return new DoneCommand(descriptionArr[1]);
        case "delete":
            return new DeleteCommand(descriptionArr[1]);
        case "find":
            return new FindCommand(descriptionArr[1]);
        case "todo":
            return new AddToDoCommand(descriptionArr[1]);
        case "deadline":
            return new AddDeadlineCommand(descriptionArr[1]);
        case "event":
            return new AddEventCommand(descriptionArr[1]);
        case "bye":
            return new ByeCommand("bye");
        default:
            throw new IncorrectTypeException("");
        }
    }
}
