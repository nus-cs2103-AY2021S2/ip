package duke.parser;

import duke.Exceptions.DukeException;
import duke.Exceptions.EmptyLineException;
import duke.Exceptions.IncorrectTypeException;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.showListCommand;

public class Parser {

    /**
     * Returns a command on what to do with the given description.
     *
     * @param input String of an input.
     * @return Command.
     * @throws DukeException If the input is blank.
     */
    public static Command parseTask(String input) throws DukeException {
        String[] line = input.split(" ", 2); // split type of task from description
        String type = line[0]; // type of task
        if (input.isBlank()) {
            throw new EmptyLineException(" ");
        }
        if (line.length != 2) {
            line = new String[]{type, ""};
        }
        switch (type) {
        case "list":
            return new showListCommand("list");
        case "done":
            return new DoneCommand(line[1]);
        case "delete":
            return new DeleteCommand(line[1]);
        case "find":
            return new FindCommand(line[1]);
        case "todo":
            return new AddToDoCommand(line[1]);
        case "deadline":
            return new AddDeadlineCommand(line[1]);
        case "event":
            return new AddEventCommand(line[1]);
        case "bye":
            return new ByeCommand("bye");
        default:
            throw new IncorrectTypeException("");
        }
    }
}
