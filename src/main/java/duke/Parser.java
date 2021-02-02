package main.java.duke;

import main.java.duke.command.*;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Parser {

    private String input;
    private String command;

    public Parser() {
    }

    /**
     * Returns a Command after parsing the input that is received from the user.
     * Invalid input would result in DukeException being thrown.
     * @param input: Input receives from the user
     * @return Command: specific Command relating to the input which requires execution
     * @throws DukeException: Exception is thrown when an invalid command is given
     */
    public Command parse(String input) throws DukeException {
        String[] args = input.split(" ");
        this.input = input;
        this.command = args[0];

        switch (this.command) {
            case "list":
                String[] listParams = input.split("list ");
                if (listParams.length == 1) {
                    return new ShowTaskCommand();
                } else if (listParams[1].equals("today")) {
                    return new ShowTaskCommand(LocalDate.now());
                } else if (listParams[1].equals("tomorrow")) {
                    return new ShowTaskCommand(LocalDate.now().plus(1, ChronoUnit.DAYS));
                } else {
                    return new ShowTaskCommand(listParams[1]);
                }
            case "todo":
                String todoName = validateOneField("There\'s no task name specified!");
                Todo todo = new Todo(todoName);
                return new AddTaskCommand(todo);
            case "deadline":
                String[] deadlineDetails = validateTwoFieldWithDivider("/by",
                            "There\'s no date specified!",
                            "\"Your date/time must be in the yyyy-mm-dd format. Please try again!");

                try {
                    Deadline deadline = new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1]));
                    return new AddTaskCommand(deadline);
                } catch (DateTimeParseException dtEx) {
                    throw new DukeException("\"Your date/time must be in the yyyy-mm-dd format. Please try again!");
                }
            case "event":
                String[] eventDetails = validateTwoFieldWithDivider("/at",
                        "There\'s no date specified!",
                        "\"Your date/time must be in the yyyy-mm-dd format. Please try again!");

                try {
                    Event event = new Event(eventDetails[0], LocalDate.parse(eventDetails[1]));
                    return new AddTaskCommand(event);
                } catch (DateTimeParseException dtEx) {
                    throw new DukeException("\"Your date/time must be in the yyyy-mm-dd format. Please try again!");
                }
            case "done":
                String doneIndex = validateOneField("There\'s no task index specified!");
                return new DoneTaskCommand(Integer.parseInt(doneIndex));
            case "find":
                String findCriteria = validateOneField("There\'s no criteria specified!");
                return new FindCommand(findCriteria);
            case "delete":
                String deleteIndex = validateOneField("There\'s no task index specified!");
                return new DeleteTaskCommand(Integer.parseInt(deleteIndex));
            case "bye":
                return new ByeCommand();
            default:
                throw new DukeException("There\'s no such command! Try todo?");
        }
    }

    private String validateOneField(String exceptionDesc) throws DukeException {
        String[] params = this.input.split(this.command + " ");
        if (params.length == 2) {
            return params[1];
        } else {
            throw new DukeException(exceptionDesc);
        }
    }

    private String[] validateTwoFieldWithDivider(String divider, String exceptionOneDesc, String exceptionTwoDesc) throws DukeException {
        String[] params = input.split( this.command + " ");
        if (params.length == 2) {
            String[] details = params[1].split(divider);
            if (details.length == 2) {
                return details;
            } else {
                throw new DukeException(exceptionTwoDesc);
            }
        } else {
            throw new DukeException(exceptionOneDesc);
        }
    }
}
