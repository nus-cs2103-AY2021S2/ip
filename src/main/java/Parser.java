import exceptions.DukeNoDescriptionException;
import exceptions.DukeUnknownArgumentsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static final String DEADLINE_COMMAND = "deadline";
    private static final String DONE_COMMAND = "done";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final int TODO_MIN_ARGUMENTS = 2;
    public static final int TODO_DESCRIPTION = 5;
    public static final int DATE_PARAM = 1;
    public static final int DESCRIPTION_PARAM = 0;
    public static final String DATE_SEPARATOR = "/";
    public static final int DATE_INPUT_MIN_ARGUMENTS = 4;
    public static final int INDEX_PADDING = 1;

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

    static int stringToIndex(String input, int i) {
        return Integer.parseInt(input.substring(i)) - INDEX_PADDING;
    }

    static AddCommandType inputToAddCommand(String input) throws DukeUnknownArgumentsException {
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

    static String parseTodoInput(String input) throws DukeNoDescriptionException {
        if (input.split(" ").length < TODO_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(TODO_COMMAND);
        } else {
            return input.substring(TODO_DESCRIPTION);
        }
    }

    static LocalDate obtainDate(String input, AddCommandType command) {
        input = input.substring(command.getPostfix());
        String[] inputs = input.split(DATE_SEPARATOR);
        return LocalDate.parse(inputs[DATE_PARAM]);
    }

    static String obtainDescription(String input, AddCommandType command) throws DukeNoDescriptionException {
        if (input.split(" ").length < DATE_INPUT_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(command.getName());
        } else {
            input = input.substring(command.getPostfix());
            String[] inputs = input.split(DATE_SEPARATOR);
            return inputs[DESCRIPTION_PARAM];
        }
    }
}
