package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Arrays;

public class Parser {
    public static final Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ");
        String command = inputs[0].toUpperCase();
        if (!DukeCommand.isContains(command)) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else {
            DukeCommand dukeCommand = DukeCommand.valueOf(command);
            switch (dukeCommand) {
            case BYE:
                return new ExitCommand();
            case DELETE:
                return new DeleteCommand(validateInput(inputs));
            case DONE:
                return new DoneCommand(validateInput(inputs));
            case EVENT:
            case DEADLINE:
                return new AddCommand(processInputs(inputs, dukeCommand));
            case LIST:
                if (inputs.length == 2) {
                    return new ListCommand(inputs[1]);
                }
                return new ListCommand();
            case TODO:
                if (inputs.length < 2) {
                    throw new DukeException("The description of todo cannot be empty.");
                }
                ToDo newToDo = new ToDo(String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length)));
                return new AddCommand(newToDo);
            default:
                return null;
            }
        }
    }

    public static int validateInput(String[] arr) throws DukeException {
        if (arr.length < 2) {
            throw new DukeException("You are missing the index!");
        }
        return Integer.parseInt(arr[1]) - 1;
    }

    public static Task processInputs(String[] arr, DukeCommand dukeCommand) throws DukeException {
        String commandType = dukeCommand.name().toLowerCase();
        if (arr.length < 2) {
            throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
        }

        String delimiter = dukeCommand == DukeCommand.DEADLINE ? "/by" : "/at";
        int index = Arrays.asList(arr).indexOf(delimiter);
        if (index < 0) {
            throw new DukeException(String.format("You have entered an invalid %s format.", commandType));
        }

        if (arr[index-1].equals(commandType)) {
            throw new DukeException(String.format("The description of %s cannot be empty.", commandType));
        }

        if (arr.length <= index+1) {
            throw new DukeException(String.format("The date of %s cannot be empty.", commandType));
        }

        String desc = String.join(" ", Arrays.copyOfRange(arr, 1, index));
        String date = arr[index+1];

        if (!DateHelper.checkIsValidDate(date)) {
            throw new DukeException("You have entered an invalid date format.");
        }

        if (dukeCommand == DukeCommand.DEADLINE) {
            return new Deadline(desc, date);
        } else {
            return new Event(desc, date);
        }
    }
}