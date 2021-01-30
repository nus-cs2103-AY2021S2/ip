package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    /**
     * Returns a command based on user input
     *
     * @param userInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parse(String userInput) throws DukeException {
        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else {
            String[] splitInput = userInput.split(" ", 2);
            switch (splitInput[0]) {

            case "done":
                if (splitInput.length < 2) {
                    throw new DukeException("The task index is missing.");
                } else {
                    return new DoneCommand(Integer.parseInt(splitInput[1]));
                }

            case "delete":
                if (splitInput.length < 2) {
                    throw new DukeException("The task index is missing.");
                } else {
                    return new DeleteCommand(Integer.parseInt(splitInput[1]));
                }

            case "todo":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    return new AddCommand(new Todo(splitInput[1]));
                }

            case "deadline":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    String[] splitDeadlineInput = splitInput[1].split(" /by ");
                    if (splitDeadlineInput.length < 2) {
                        throw new DukeException("Insufficient info given for a deadline.");
                    } else {
                        return new AddCommand(new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]));
                    }
                }

            case "event":
                if (splitInput.length < 2) {
                    throw new DukeException("The description of an event cannot be empty.");
                } else {
                    String[] splitEventInput = splitInput[1].split(" /at ");
                    if (splitEventInput.length < 2) {
                        throw new DukeException("Insufficient info given for a deadline.");
                    } else {
                        return new AddCommand(new Event(splitEventInput[0], splitEventInput[1]));
                    }
                }

            case "find":
                if (splitInput.length < 2) {
                    throw new DukeException("Keyword is missing");
                } else {
                    return new FindCommand(splitInput[1]);
                }

            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
