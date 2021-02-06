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
        }
        String[] splitInput = userInput.split(" ", 2);
        switch (splitInput[0]) {
        case "done":
            return parseDone(splitInput);
        case "delete":
            return parseDelete(splitInput);
        case "todo":
            return parseTodo(splitInput);
        case "deadline":
            return parseDeadline(splitInput);
        case "event":
            return parseEvent(splitInput);
        case "find":
            return parseFind(splitInput);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns a DoneCommand based on user input.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseDone(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("The task index is missing.");
        }
        return new DoneCommand(Integer.parseInt(splitInput[1]));
    }

    /**
     * Returns a DeleteCommand based on user input.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseDelete(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("The task index is missing.");
        }
        return new DeleteCommand(Integer.parseInt(splitInput[1]));
    }

    /**
     * Returns a AddCommand that will create a new Todo task based on user input.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseTodo(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(splitInput[1]));
    }

    /**
     * Returns a AddCommand that will create a new Deadline based on user input.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseDeadline(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] splitDeadlineInput = splitInput[1].split(" /by ");
        if (splitDeadlineInput.length < 2) {
            throw new DukeException("Insufficient info given for a deadline.");
        }
        return new AddCommand(new Deadline(splitDeadlineInput[0], splitDeadlineInput[1]));
    }

    /**
     * Returns a AddCommand that will create a new Event based on user input.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseEvent(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] splitEventInput = splitInput[1].split(" /at ");
        if (splitEventInput.length < 2) {
            throw new DukeException("Insufficient info given for a deadline.");
        }
        return new AddCommand(new Event(splitEventInput[0], splitEventInput[1]));
    }

    /**
     * Returns a FindCommand that will find tasks that match the provided keyword.
     *
     * @param splitInput
     * @return Command
     * @throws DukeException
     */
    public static final Command parseFind(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("Keyword is missing");
        }
        return new FindCommand(splitInput[1]);
    }

}
