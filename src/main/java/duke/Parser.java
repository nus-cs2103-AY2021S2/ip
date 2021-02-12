package duke;

/**
 * Represents a Parser which can parse user input.
 */
public class Parser {

    /**
     * Returns a 2 element String array delimited by a spacebar, regardless of input.
     * If input is 1 word, the second element is an empty string.
     * If input is 0 words, both elements are empty strings.
     *
     * @param command String to be parsed.
     * @return String array with 2 elements.
     */
    public static String[] parseCommand(String command) {
        assert command.length() != 0 : "Command String is empty";
        String[] stringArr = command.split(" ", 2);
        if (stringArr.length == 1) {
            stringArr = new String[]{command, ""};
        }
        return stringArr;
    }

    /**
     * Returns String array with size depending on the command.
     *
     * @param command the command that is given by the user.
     * @param params String to be parsed.
     * @return Parsed String array.
     * @throws DukeException if the required parameters for the specific command are missing.
     */
    public static String[] parseParams(DukeCommand command, String params) throws DukeException {
        String[] paramArr;
        if (params.length() == 0) {
            if (command == DukeCommand.DELETE) {
                throw new DukeException("A number must be provided.");
            }
            throw new DukeException("The description of a "
                    + command.name().toLowerCase() + " cannot be empty.");
        }
        assert command != null : "DukeCommand cannot be null";
        switch (command) {
        case DELETE:
            // Fallthrough
        case DONE:
            // Fallthrough
        case FIND:
            // Fallthrough
        case TODO:
            paramArr = new String[] {params};
            break;
        case EVENT:
            paramArr = params.split(" /at ", 2);
            if (paramArr.length != 2) {
                throw new DukeException("Event Timing cannot be empty.");
            }
            break;
        case DEADLINE:
            paramArr = params.split(" /by ", 2);
            if (paramArr.length != 2) {
                throw new DukeException("Deadline Timing cannot be empty.");
            }
            break;
        default:
            throw new DukeException("Invalid command.");
        }
        return paramArr;
    }

    /**
     * Parses input from the file and returns a Task
     *
     * @param line String repesenting a task.
     * @return Task that the input from the file represents
     * @throws DukeException if the tasktype is invalid.
     */
    public static Task parseTaskFromFile(String line) throws DukeException {
        assert line.length() != 0 : "Line cannot be empty";
        assert line.contains(" ~ ") : "Line must be delimited by ' ~ '";
        String[] parsedLine = line.split(" ~ ");
        TaskType taskType = TaskType.fromString(parsedLine[0]);
        Boolean isDone = (Integer.valueOf(parsedLine[1]) == 1) ? true : false;
        String description = parsedLine[2];
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new Todo(description, taskType);
            break;
        case EVENT:
            newTask = new Event(description, taskType, parsedLine[3]);
            break;
        case DEADLINE:
            newTask = new Deadline(description, taskType, parsedLine[3]);
            break;
        default:
            throw new DukeException("Invalid TaskType.");
        }
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Parses an int from a string, and throws a DukeException if the input
     * is not an integer.
     *
     * @param integerString String that represents an integer.
     * @return an Integer.
     * @throws DukeException if the input cannot be parsed to an integer.
     */
    public static int parseInt(String integerString) throws DukeException {
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Integer");
        }
    }
}
