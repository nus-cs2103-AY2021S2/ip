package duke;

import duke.commands.AddCommandType;
import duke.commands.CommandType;
import duke.exceptions.DukeCorruptedStorageException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeUnknownArgumentsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static final String DEADLINE_COMMAND = "deadline";
    private static final String DONE_COMMAND = "done";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final int TODO_MIN_ARGUMENTS = 2;
    public static final int TODO_DESCRIPTION = 5;
    public static final int ENCODE_DATE_PARAM = 3;
    public static final int DESCRIPTION_PARAM = 0;
    public static final String DATE_SEPARATOR = "/";
    public static final int DATE_INPUT_MIN_ARGUMENTS = 4;
    public static final int INDEX_PADDING = 1;
    public static final String TODO_COMMAND_TYPE = "T";
    public static final String DEADLINE_COMMAND_TYPE = "D";
    public static final String EVENT_COMMAND_TYPE = "E";
    public static final String DATA_SEPARATOR = " \\| ";
    public static final int TODO_COMMAND_TYPE_PARAM = 0;
    public static final int TODO_DESCRIPTION_PARAM = 2;
    public static final int IS_DONE_PARAM = 1;
    public static final String DONE_ENCODING = "1";
    public static final String NOT_DONE_ENCODING = "0";
    public static final int DATE_PARAM = 1;
    public static final int DATE_POSTFIX = 3;

    public static String localDateToString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dtf);
    }

    static CommandType parseCommand(String input) {
        if (input.startsWith(DONE_COMMAND)) {
            return CommandType.DONE;
        } else if (input.startsWith(LIST_COMMAND)) {
            return CommandType.LIST;
        } else if (input.startsWith(DELETE_COMMAND)) {
            return CommandType.DELETE;
        } else {
            return CommandType.ADD;
        }
    }

    public static int stringToIndex(String input, int i) {
        return Integer.parseInt(input.substring(i)) - INDEX_PADDING;
    }

    public static AddCommandType inputToAddCommand(String input) throws DukeUnknownArgumentsException {
        if (input.startsWith(TODO_COMMAND)) {
            return AddCommandType.TODO;
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            return AddCommandType.DEADLINE;
        } else if (input.startsWith(EVENT_COMMAND)) {
            return AddCommandType.EVENT;
        } else {
            throw new DukeUnknownArgumentsException();
        }
    }

    public static String parseTodoInput(String input) throws DukeNoDescriptionException {
        if (input.split(" ").length < TODO_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(TODO_COMMAND);
        } else {
            return input.substring(TODO_DESCRIPTION).trim();
        }
    }

    public static LocalDate obtainDate(String input, AddCommandType command) {
        input = input.substring(command.getPostfix());
        String[] inputs = input.split(DATE_SEPARATOR);
        return LocalDate.parse(inputs[DATE_PARAM].substring(DATE_POSTFIX));
    }

    public static String obtainDescription(String input, AddCommandType command) throws DukeNoDescriptionException {
        if (input.split(" ").length < DATE_INPUT_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(command.getName());
        } else {
            input = input.substring(command.getPostfix());
            String[] inputs = input.split(DATE_SEPARATOR);
            return inputs[DESCRIPTION_PARAM];
        }
    }

    public static AddCommandType parseCommandType(String input) throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        String command = separatedInput[TODO_COMMAND_TYPE_PARAM];
        switch (command) {
        case TODO_COMMAND_TYPE:
            return AddCommandType.TODO;
        case DEADLINE_COMMAND_TYPE:
            return AddCommandType.DEADLINE;
        case EVENT_COMMAND_TYPE:
            return AddCommandType.EVENT;
        default:
            throw new DukeCorruptedStorageException();
        }
    }

    public static String obtainEncodedDescription(String input)
            throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        if (separatedInput[TODO_DESCRIPTION_PARAM].isBlank()) {
            throw new DukeCorruptedStorageException();
        }
        return separatedInput[TODO_DESCRIPTION_PARAM];
    }

    public static boolean isEncodedTaskDone(String input) throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        String isDone = separatedInput[IS_DONE_PARAM];
        if (isDone.equals(DONE_ENCODING)) {
            return true;
        } else if (isDone.equals(NOT_DONE_ENCODING)) {
            return false;
        } else {
            throw new DukeCorruptedStorageException();
        }
    }

    public static LocalDate obtainEncodedDate(String input) throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        String date = separatedInput[ENCODE_DATE_PARAM];
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeCorruptedStorageException();
        }
    }
}
