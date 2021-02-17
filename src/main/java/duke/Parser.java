package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.DeadlineException;
import duke.task.Event;
import duke.task.EventException;
import duke.task.ToDo;
import duke.task.ToDoException;

/**
 * Parser for Duke commands
 */
public class Parser {
    /**
     * Parses a string input to the relevent command instruction.
     * @param input The String to be parsed
     * @return Duke's response to the input as a String
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        switch (input) {

        case "list":
            return new ListCommand();

        case "help":
            return HelpCommand.getHelpAllCommand();

        default:
            String[] splitInput = input.split(" ", 2);

            switch (splitInput[0]) {
            case "help":
                return getHelpCommand(splitInput[1].strip());

            case "done":
                checkDoneFormat(splitInput);
                return getDoneCommand(splitInput[1]);

            case "delete":
                checkDeleteFormat(splitInput);
                return getDeleteCommand(splitInput[1]);

            case "check":
                checkCheckFormat(splitInput);
                return getCheckCommand(splitInput[1]);

            case "find":
                checkFindFormat(splitInput);
                return new FindCommand(splitInput[1].strip());

            case "todo":
                checkToDoFormat(splitInput);
                ToDo todo = new ToDo(splitInput[1]);
                return new AddCommand(todo);

            case "deadline":
                checkDeadlineFormat(splitInput);
                Deadline deadline = getDeadline(splitInput[1]);
                return new AddCommand(deadline);

            case "event":
                checkEventFormat(splitInput);
                Event event = getEvent(splitInput[1]);
                return new AddCommand(event);

            default:
                throw new DukeException("I'm sorry :(\nI don't know what that means\n"
                        + "Type 'help' in the chat to see the list of valid commands");
            }
        }
    }

    private static void checkDoneFormat(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("Please enter the task to mark as finished!");
        }
    }

    private static void checkDeleteFormat(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("Please enter the task to delete!");
        }
    }

    private static void checkCheckFormat(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("Please enter the date to check!");
        }
    }

    private static void checkFindFormat(String[] splitInput) throws DukeException {
        if (splitInput.length < 2) {
            throw new DukeException("Please enter the keyword(s) to find!");
        }
    }

    private static void checkToDoFormat(String[] splitInput) throws ToDoException {
        if (splitInput.length < 2) {
            throw new ToDoException("Todo details cannot be empty!");
        }
    }

    private static void checkDeadlineFormat(String[] splitInput) throws DeadlineException {
        if (splitInput.length < 2) {
            throw new DeadlineException("Deadline details cannot be empty!");
        }
    }

    private static void checkEventFormat(String[] splitInput) throws EventException {
        if (splitInput.length < 2) {
            throw new EventException("Event details cannot be empty!");
        }
    }

    // get methods
    private static Command getHelpCommand(String command) throws DukeException {
        switch (command) {
        case "todo":
            return HelpCommand.getHelpToDoCommand();
        case "deadline":
            return HelpCommand.getHelpDeadlineCommand();
        case "event":
            return HelpCommand.getHelpEventCommand();
        case "list":
            return HelpCommand.getHelpListCommand();
        case "done":
            return HelpCommand.getHelpDoneCommand();
        case "delete":
            return HelpCommand.getHelpDeleteCommand();
        case "find":
            return HelpCommand.getHelpFindCommand();
        case "check":
            return HelpCommand.getHelpCheckCommand();
        default:
            throw new DukeException(command + " is not a valid command!\n"
                    + "Type 'help' in the chat to see the list of valid commands");
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
            assert(splitDetails.length == 2);

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
            assert(splitDetails.length == 2);

            try {
                LocalDate date = LocalDate.parse(splitDetails[1]);
                return new Event(splitDetails[0], date);
            } catch (DateTimeParseException e) {
                throw new EventException("Cannot read date!");
            }
        }
    }

}
