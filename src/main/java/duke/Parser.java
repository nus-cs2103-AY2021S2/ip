package duke;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.CheckCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import duke.task.Deadline;
import duke.task.DeadlineException;
import duke.task.Event;
import duke.task.EventException;
import duke.task.ToDo;
import duke.task.ToDoException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser for Duke commands
 */
public class Parser {

    /**
     * Parses a string input to the relevent command instruction. 
     * 
     * @param input The String to be parsed
     * @return Command to be executed
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException{
        switch (input) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        default:
            String[] splitInput = input.split(" ", 2);

            switch (splitInput[0]) {
            case "done":
                if (splitInput.length < 2) {
                    throw new DukeException("Please enter the task to mark as finished!");
                }
                return getDoneCommand(splitInput[1]);
        
            case "delete":
                if (splitInput.length < 2) {
                    throw new DukeException("Please enter the task to delete!");
                }
                return getDeleteCommand(splitInput[1]);

            case "check":
                if (splitInput.length < 2) {
                    throw new DukeException("Please enter the date to check!");
                }
                return getCheckCommand(splitInput[1]);

            case "todo":
                if (splitInput.length < 2) {
                    throw new ToDoException("Todo details cannot be empty!");
                }
                ToDo todo = new ToDo(splitInput[1]);
                return new AddCommand(todo);

            case "deadline":
                if (splitInput.length < 2) {
                    throw new DeadlineException("Deadline details cannot be empty!");
                }
                Deadline deadline = getDeadline(splitInput[1]);
                return new AddCommand(deadline);

            case "event":
                if (splitInput.length < 2) {
                    throw new EventException("Event details cannot be empty!");
                }
                Event event = getEvent(splitInput[1]);
                return new AddCommand(event);                

            default:
                throw new DukeException("I'm sorry ☹\nI don't know what that means");
            }
        }
    }

    private static Command getDoneCommand(String taskNumString) throws DukeException {
        try {
            int taskNum = Integer.parseInt(taskNumString);
            return new DoneCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("Too many inputs!\nPlease re-enter the command in the format: done <task number>");
        }
    }

    private static Command getDeleteCommand(String taskNumString) throws DukeException {
        try {
            int taskNum = Integer.parseInt(taskNumString);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "Too many inputs!\nPlease re-enter the command in the format: delete <task number>");
        }
    }

    private static Command getCheckCommand(String dateString) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateString);
            return new CheckCommand(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Cannot read date!\nPlease re-enter the command in the format: check <YYYY-MM-DD>");
        }
    }

    private static Deadline getDeadline(String detail) {
        if (detail.indexOf(" /by ") < 0) {
            throw new DeadlineException("Missing a due date!");
        }

        String[] splitDetails = detail.split(" /by ");

        if (splitDetails.length < 2) {
            throw new DeadlineException("Deadline details cannot be empty!");
        } else if (splitDetails.length > 2) {
            throw new DeadlineException("Too many dates given!");
        } else {
            try {
                LocalDate date = LocalDate.parse(splitDetails[1]);
                return new Deadline(splitDetails[0], date);
            } catch (DateTimeParseException e) {
                throw new DeadlineException("Cannot read date!");
            }
        }
    }

    private static Event getEvent(String detail) {
        if (detail.indexOf(" /at ") < 0) {
            throw new EventException("Missing a due date!");
        }

        String[] splitDetails = detail.split(" /at ");

        if (splitDetails.length < 2) {
            throw new EventException("Event details cannot be empty!");
        } else if (splitDetails.length > 2) {
            throw new EventException("Too many dates given!");
        } else {
            try {
                LocalDate date = LocalDate.parse(splitDetails[1]);
                return new Event(splitDetails[0], date);
            } catch (DateTimeParseException e) {
                throw new EventException("Cannot read date!");
            }
        }
    }

}
