package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.BasicCommandType;
import duke.commands.SpecificCommandType;
import duke.exceptions.DukeCorruptedStorageException;
import duke.exceptions.DukeNoDescriptionException;
import duke.exceptions.DukeUnknownArgumentsException;

/**
 * Represents a Parser class to parse input to specified outputs based on inputs.
 */
public class Parser {
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DONE_COMMAND = "done";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String FIND_COMMAND = "find";
    private static final int TODO_MIN_ARGUMENTS = 2;
    private static final int TODO_DESCRIPTION = 5;
    private static final int ENCODE_DATE_PARAM = 3;
    private static final int DESCRIPTION_PARAM = 0;
    private static final String DATE_SEPARATOR = "/";
    private static final int DATE_INPUT_MIN_ARGUMENTS = 4;
    private static final int INDEX_PADDING = 1;
    private static final String TODO_COMMAND_TYPE = "T";
    private static final String DEADLINE_COMMAND_TYPE = "D";
    private static final String EVENT_COMMAND_TYPE = "E";
    private static final String DATA_SEPARATOR = " \\| ";
    private static final int TODO_COMMAND_TYPE_PARAM = 0;
    private static final int TODO_DESCRIPTION_PARAM = 2;
    private static final int IS_DONE_PARAM = 1;
    private static final String DONE_ENCODING = "1";
    private static final String NOT_DONE_ENCODING = "0";
    private static final int DATE_PARAM = 1;
    private static final int DATE_POSTFIX = 3;
    private static final int FIND_MIN_ARGUMENTS = 2;

    /**
     * Return a string representation based on LocalDate.
     * @param date date used to create the string representation.
     * @return the date with "MMM dd yyyy".
     */
    public static String localDateToString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dtf);
    }

    /**
     * Return BasicCommandType based on input.
     * @param input user input used to return a BasicCommandType.
     * @return BasicCommandType.
     */
    static BasicCommandType parseCommand(String input) {
        if (input.startsWith(DONE_COMMAND)) {
            return BasicCommandType.DONE;
        } else if (input.startsWith(LIST_COMMAND)) {
            return BasicCommandType.LIST;
        } else if (input.startsWith(DELETE_COMMAND)) {
            return BasicCommandType.DELETE;
        } else {
            return BasicCommandType.ADD;
        }
    }

    /**
     * Return index based on string input.
     * @param input input used to get index.
     * @param i when the string representation of the index starts.
     * @return index based on the input.
     */
    public static int stringToIndex(String input, int i) {
        return Integer.parseInt(input.substring(i)) - INDEX_PADDING;
    }

    /**
     * Return SpecificCommandType based on input: TODO, DEADLINE, EVENT, FIND.
     * @param input user input used to get SpecificCommandType.
     * @return SpecificCommandType based on input.
     * @throws DukeUnknownArgumentsException when the input contains an unknown command.
     */
    public static SpecificCommandType inputToSpecificCommand(String input)
            throws DukeUnknownArgumentsException {
        if (input.startsWith(TODO_COMMAND)) {
            return SpecificCommandType.TODO;
        } else if (input.startsWith(DEADLINE_COMMAND)) {
            return SpecificCommandType.DEADLINE;
        } else if (input.startsWith(EVENT_COMMAND)) {
            return SpecificCommandType.EVENT;
        } else if (input.startsWith(FIND_COMMAND)) {
            return SpecificCommandType.FIND;
        } else {
            throw new DukeUnknownArgumentsException();
        }
    }

    /**
     * Return description of the Todo based on input.
     * @param input user input to get description of Todo.
     * @return description of Todo based on input.
     * @throws DukeNoDescriptionException when the description is empty.
     */
    public static String parseTodoInput(String input) throws DukeNoDescriptionException {
        if (input.split(" ").length < TODO_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(TODO_COMMAND);
        } else {
            return input.substring(TODO_DESCRIPTION).trim();
        }
    }

    /**
     * Return description from the find input.
     * @param input find input used to get the description.
     * @return the description of the find input.
     * @throws DukeNoDescriptionException if there is no description.
     */
    public static String parseFindInput(String input) throws DukeNoDescriptionException {
        if (input.split(" ").length < FIND_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(SpecificCommandType.FIND.getName());
        } else {
            return input.substring(SpecificCommandType.FIND.getPostfix());
        }
    }

    /**
     * Return LocalDate based on input and AddCommandType's postfix.
     * @param input user input to get Task date representation.
     * @param command AddCommandType to be used to distinguish how to get the LocalDate.
     * @return LocalDate based on the string representation of the date.
     */
    public static LocalDate obtainDate(String input, SpecificCommandType command) {
        input = input.substring(command.getPostfix());
        String[] inputs = input.split(DATE_SEPARATOR);
        return LocalDate.parse(inputs[DATE_PARAM].substring(DATE_POSTFIX));
    }

    /**
     * Return description of the Event and Deadline Task, depending on the command.
     * @param input user input to get the description of the Event or Deadline.
     * @param command AddCommandType used to differentiate the Event and Deadline.
     * @return description of either Event or Deadline.
     * @throws DukeNoDescriptionException when the description of the input is empty.
     */
    public static String obtainDescription(String input, SpecificCommandType command)
            throws DukeNoDescriptionException {
        if (input.split(" ").length < DATE_INPUT_MIN_ARGUMENTS) {
            throw new DukeNoDescriptionException(command.getName());
        } else {
            input = input.substring(command.getPostfix());
            String[] inputs = input.split(DATE_SEPARATOR);
            return inputs[DESCRIPTION_PARAM];
        }
    }

    /**
     * Return AddCommandType based on encoded input.
     * @param input used to get encoded representation to get the AddCommandType.
     * @return TODO if "T", DEADLINE if "D", EVENT if "E".
     * @throws DukeCorruptedStorageException when the encoded command is unknown.
     */
    public static SpecificCommandType parseCommandType(String input)
            throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        String command = separatedInput[TODO_COMMAND_TYPE_PARAM];
        switch (command) {
        case TODO_COMMAND_TYPE:
            return SpecificCommandType.TODO;
        case DEADLINE_COMMAND_TYPE:
            return SpecificCommandType.DEADLINE;
        case EVENT_COMMAND_TYPE:
            return SpecificCommandType.EVENT;
        default:
            throw new DukeCorruptedStorageException();
        }
    }

    /**
     * Return description of task based on encoded input.
     * @param input encoded input from save file.
     * @return description of task.
     * @throws DukeCorruptedStorageException when the encoded input is not of the right format.
     */
    public static String obtainEncodedDescription(String input)
            throws DukeCorruptedStorageException {
        String[] separatedInput = input.split(DATA_SEPARATOR);
        if (separatedInput[TODO_DESCRIPTION_PARAM].isBlank()) {
            throw new DukeCorruptedStorageException();
        }
        return separatedInput[TODO_DESCRIPTION_PARAM];
    }

    /**
     * Return true if encoded task is marked as done, otherwise false.
     * @param input encoded task from save file.
     * @return true if encoded task is marked as done.
     * @throws DukeCorruptedStorageException when the encoded task is not of the right format.
     */
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

    /**
     * Return LocalDate of either the Event's date or Deadline's date.
     * @param input encoded task used to get the LocalDate.
     * @return LocalDate based on input.
     * @throws DukeCorruptedStorageException when the encoded task is not of the right format.
     */
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
