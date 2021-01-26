package duke;

public class Parser {
    public static String[] parseCommand(String command) {
        String[] stringArr = command.split(" ", 2);
        if (stringArr.length == 1) {
            stringArr = new String[]{command,""};
        }
        return stringArr;
    }

    public static String[] parseParams(DukeCommand command, String params) throws DukeException {
        String[] paramArr;
        if (params.length() == 0) {
            if (command == DukeCommand.DELETE) {
                throw new DukeException("A number must be provided.");
            }
            throw new DukeException("The description of a " 
                    + command.name().toLowerCase() + " cannot be empty.");
        }
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

    public static Task parseTaskFromFile(String line) throws DukeException {
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

    public static int parseInt(String integerString) throws DukeException {
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Integer");
        }

    }
}
