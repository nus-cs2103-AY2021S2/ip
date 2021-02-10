import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    private static final String ERROR_DESCRIPTION = "OOPS!!! The description cannot be empty.";
    private static final String ERROR_SEARCH_TERM = "OPPS!!! The search term for find cannot be empty.";
    private static final String ERROR_INVALID_COMMAND = "OOPS!!! I`m sorry. but i don`t know what that means :-(";

    /**
     * Returns a Command object based on the fullCommand given
     * @param fullCommand (command entered by the user)
     * @return Command comamnd
     * @throws DukeException
     * @throws DukeDeadlineException
     */
    public static Command parse(String fullCommand) throws DukeException , DukeDeadlineException {
        String[] commandParts = getKeyword(fullCommand);
        checkKeyword(commandParts);
        return createCommand(commandParts);
    }

    private static String[] getKeyword(String command) {
        ArrayList<String> commandParts = new ArrayList<>();
        String trimCommand = command.trim();
        int firstSpace = trimCommand.indexOf(" ");
        if (firstSpace == -1) {
            commandParts.add(trimCommand);
        } else {
            String keyword = trimCommand.substring(0, firstSpace);
            commandParts.add(keyword);
            commandParts.add(trimCommand.substring(firstSpace));
        }

        return commandParts.toArray(new String[commandParts.size()]);
    }

    private static void checkKeyword (String[] commandParts) throws DukeException {
        if (commandParts.length == 0) {
            throw new DukeException("Please enter a command");
        }
        switch (commandParts[0].toLowerCase()) {
        case"bye":
        case"list":
        case"done":
        case"delete":
        case "find":
        case"todo":
        case"deadline":
        case"event":
            break;
        default:
            throw new DukeException("Invalid command");
        }
    }

    private static Command createCommand(String[] commandParts) throws DukeException , DukeDeadlineException {
        String keyword = commandParts[0].toLowerCase();
        boolean requireDesc = checkRequireDescription(keyword);
        boolean requireOption = checkRequireOption(keyword);
        boolean requireDeadline = checkRequireDeadline(keyword);
        if (!requireDesc) {
            return createWithoutDescCommand(commandParts);
        }

        if (requireOption) {
            checkOption(commandParts);
            return createWithOptionCommand(commandParts);
        }

        if (!requireDeadline) {
            return createWithoutDeadlineCommand(commandParts);
        }

        checkDescription(commandParts, ERROR_DESCRIPTION);
        return createAddCommand(commandParts);
    }

    private static boolean checkRequireDescription(String keyword) {
        switch (keyword) {
        case"bye":
        case"list":
            return false;
        default:
            return true;
        }
    }

    private static boolean checkRequireOption(String keyword) {
        switch (keyword) {
        case"done":
        case"delete":
            return true;
        default:
            return false;
        }
    }

    private static boolean checkRequireDeadline(String keyword) {
        switch(keyword) {
        case "find":
        case "todo":
            return false;
        default:
            return true;
        }
    }

    private static Command createWithoutDescCommand(String[] commandParts) throws DukeException {
        String keyword = commandParts[0];
        if (keyword.equalsIgnoreCase("bye")) {
            return new SaveCommand(keyword);
        } else if (keyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else {
            throw new DukeException("Invalid Command");
        }
    }

    private static void checkOption(String[] commandParts) throws DukeException {
        checkDescription(commandParts, "Command is without any option");
    }

    private static Command createWithOptionCommand(String[] commandParts) throws DukeException {
        String keyword = commandParts[0];

        try {
            String remainderCommand = commandParts[1].trim();
            int option = Integer.parseInt(remainderCommand) - 1;
            switch (keyword) {
            case "done" :
                return new DoneCommand(option);
            case "delete":
                return new DeleteCommand(option);
            default:
                throw new DukeException("Invalid Command");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("A number is missing from the command");
        }
    }

    private static Command createWithoutDeadlineCommand(String[] commandParts) throws DukeException {
        String keyword = commandParts[0];
        String remainderCommand = commandParts[1].trim();

        if (keyword.equalsIgnoreCase("find")) {
            return new FindCommand(remainderCommand);
        } else if (keyword.equalsIgnoreCase("todo")) {
            return new AddCommand(new Todo(remainderCommand));
        } else {
            throw new DukeException(ERROR_INVALID_COMMAND);
        }
    }

    private static Command createAddCommand(String[] commandParts) throws DukeException, DukeDeadlineException {
        String keyword = commandParts[0];
        String remainderCommand = commandParts[1].trim();
        Task task = null;
    }
  
    private static Task createTaskWithDeadline(String fullCommand, String keyword, int firstSpace) {
            throws DukeDeadlineException {
        assert !keyword.trim().isEmpty();

        Task t = null;
        int firstSlash = remainderCommand.indexOf("/");

        if (firstSlash == -1) {
            throw new DukeDeadlineException("OOPS!!! The deadline of a task cannot be empty.");
        }

        int nextSpace = remainderCommand.indexOf(" ", firstSlash) + 1;
        String taskDescription = remainderCommand.substring(0, firstSlash);
        String errorMessage;

        if (keyword.equalsIgnoreCase("deadline")) {
            errorMessage = "OOPS!!! Format of the deadline of a deadline task should be "
                    + "(Year-Month-Day time (24 hours)";
            LocalDateTime deadline = parseDate(remainderCommand.substring(nextSpace), errorMessage);
            task = new Deadline(taskDescription, deadline);
        } else if (keyword.equalsIgnoreCase("event")) {
            errorMessage = "OOPS!!! Format of the time period of a Event task should be "
                    + "(Year-Month-Day Time(24 hours)-Time(24 hours)";
            LocalDateTime[] deadlines = parseDates(remainderCommand.substring(nextSpace), errorMessage);
            task = new Event(taskDescription, deadlines[0], deadlines[1]);
        } else {
            throw new DukeException("Invalid Command");
        }

        return new AddCommand(task);
    }

    private static void checkDescription(String[] commandParts, String errorMessage) throws DukeException {
        if (commandParts.length != 2) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Returns a array of LocalDateTime object
     * @param data String that contains the date
     * @param errorMessage  error message to display when error is occur during prasing to LocalDateTIme
     * @return LocalDateTime[].
     * @throws DukeDeadlineException
     */

    public static LocalDateTime[] parseDates(String data, String errorMessage) throws DukeDeadlineException {
        assert !data.trim().isEmpty();

        int firstSpace = data.indexOf(" ");
        String date = data.substring(0, firstSpace);
        data = data.substring(firstSpace + 1);
        String[] timePeriod = data.split("-");
        if (timePeriod.length != 2) {
            throw new DukeDeadlineException(errorMessage);
        }
        LocalDateTime[] deadline = new LocalDateTime[2];
        for (int i = 0; i < timePeriod.length; i++) {
            deadline[i] = parseDate(date + " " + timePeriod[i], errorMessage);
        }
        return deadline;
    }

    /**
     * Returns LocalDateTime object based on string given
     * @param date String that contains the date in the format of yyyy-M-d hhmm
     * @param errorMessage error message to display when error is occur during prasing to LocalDateTIme
     * @return LocalDateTime
     * @throws DukeDeadlineException
     */
    public static LocalDateTime parseDate(String date, String errorMessage) throws DukeDeadlineException {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
        } catch (DateTimeParseException e) {
            throw new DukeDeadlineException(errorMessage);
        }
    }

    /**
     * Returns a Task Object based on the csvData provided
     * @param csvData (a row from the saved file)
     * @return
     */

    public static Task parseForText(String csvData) throws DukeDeadlineException {
        String errorMessage = "The deadline for this task is corrupted (Required : yyyy-M-d hhmm)";
        String[] taskArr = csvData.split(",");
        Task task = null;
        try {
            switch (taskArr[0]) {
            case "T":
                task = new Todo(taskArr[2]);
                break;
            case "E":
                String[] deadlineArr = {taskArr[3], taskArr[4]};
                LocalDateTime[] deadlines = new LocalDateTime[2];
                for (int i = 0; i < deadlineArr.length; i++) {
                    deadlines[i] = Parser.parseDate(deadlineArr[i], errorMessage);
                }
                task = new Event(taskArr[2], deadlines[0], deadlines[1]);
                break;
            case "D":
                LocalDateTime deadline = Parser.parseDate(taskArr[3], errorMessage);
                task = new Deadline(taskArr[2], deadline);
                break;
            }
            if (taskArr[1] == "1") {
                task.markAsDone();
            }
        } catch (DukeDeadlineException e) {
            throw e;
        }
        return task;
    }
}
