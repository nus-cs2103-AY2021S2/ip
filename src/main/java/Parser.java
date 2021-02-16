import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public static final String ERROR_INVALID_DATE_FORMAT = "OOPS!! DateTime format is incorrect. yyyy-M-dd Hmm";

    private static final String ERROR_EMPTY_DESCRIPTION = "OOPS!!! The description cannot be empty.";
    private static final String ERROR_EMPTY_SEARCH_TERM = "OPPS!!! The search term for find cannot be empty.";
    private static final String ERROR_EMPTY_COMMAND = "OPPS!!! The command cannot be empty";
    private static final String ERROR_EMPTY_DEADLINE = "OOPS!!! The deadline of a task cannot be empty.";

    private static final String ERROR_OPTION = "OOPS!! Option is missing or at the wrong place. Keyword <Option> ....";

    private static final String ERROR_INVALID_COMMAND = "OOPS!!! I`m sorry. but i don`t know what that means :-(";
    private static final String ERROR_INVALID_UPDATE_COMMAND = "OOPS!! Update command should be in this form : "
            + "Update <field> <Value> ";
    private static final String ERROR_INVALID_DEADLINE_DATETIME = "OOPS!!! Format of the deadline of "
            + "a deadline task should be (Year-Month-Day time (24 hours)";
    private static final String ERROR_INVALID_EVENT_DATETIME = "OOPS!!! Format of the time period of a Event task "
            + "should be (Year-Month-Day Time(24 hours)-Time(24 hours)";

    private static final String ERROR_LOAD_INVALID = "OPPS!!! This task seems to be corrupted";
    private static final String ERROR_LOAD_TASK_DATE = "The deadline for this task is corrupted "
            + "(Required : yyyy-M-dd Hmm)";

    /**
     * Returns a Command object based on the fullCommand given.
     *
     * @param fullCommand (command entered by the user).
     * @return Command comamnd , Command object from parsing the string entered by the user.
     * @throws DukeException , Exception regarding the command.
     * @throws DukeDeadlineException , Exception regarding the dateTime of the command.
     */
    public static Command parse(String fullCommand) throws DukeException , DukeDeadlineException {
        String[] commandParts = getKeyword(fullCommand);
        checkKeyword(commandParts);
        return createCommand(commandParts);
    }

    /**
     * Returns String array containing the keyword extracted from the command and the remaining of the command.
     *
     * @param command , String entered by the user
     * @return String[] , array containing the keyword and the remaining of the command.
     */
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

    /**
     * Checks if the keyword is a valid keyword.
     *
     * @param commandParts , Array that contains the parts of the command (Keyword , remainder).
     * @throws DukeException , Exception thrown when the keyword is not in the a accepted keyword.
     */
    private static void checkKeyword (String[] commandParts) throws DukeException {
        if (commandParts.length == 0) {
            throw new DukeException(ERROR_EMPTY_COMMAND);
        }
        switch (commandParts[0].toLowerCase()) {
        case"bye":
            //Fallthrough
        case"list":
            //Fallthrough
        case"done":
            //Fallthrough
        case"delete":
            //Fallthrough
        case "find":
            //Fallthrough
        case"todo":
            //Fallthrough
        case"deadline":
            //Fallthrough
        case"event":
            //Fallthrough
        case "update":
            break;
        default:
            throw new DukeException(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Returns a command based on the commandParts provided.
     *
     * @param commandParts , String array that contains the parts of the command (Keyword,remainder).
     * @return Command , command created based on the String array provided.
     * @throws DukeException , if there is any missing fields required by the command.
     * @throws DukeDeadlineException , if datetime provided is not of the format.
     */
    private static Command createCommand(String[] commandParts) throws DukeException , DukeDeadlineException {
        String keyword = commandParts[0].toLowerCase();
        boolean requireDesc = checkRequireDescription(keyword);
        boolean requireOption = checkRequireOption(keyword);
        boolean requireDeadline = checkRequireDeadline(keyword);
        boolean isUpdate = keyword.equalsIgnoreCase("Update");

        if (!requireDesc) {
            return createWithoutDescCommand(commandParts);
        }

        if (isUpdate) {
            checkOption(commandParts);
            return createUpdateCommand(commandParts);
        }

        if (requireOption) {
            checkOption(commandParts);
            return createWithOptionCommand(commandParts);
        }

        if (!requireDeadline) {
            String errorMessage = ERROR_EMPTY_DESCRIPTION;
            if (keyword.equalsIgnoreCase("find")) {
                errorMessage = ERROR_EMPTY_SEARCH_TERM;
            }
            checkDescription(commandParts, errorMessage);
            return createWithoutDeadlineCommand(commandParts);
        }

        checkDescription(commandParts, ERROR_EMPTY_DESCRIPTION);
        return createAddCommand(commandParts);
    }

    /**
     * Checks if the keyword requires a description.
     *
     * @param keyword , String to be checked.
     * @return boolean , if the keyword requires a description.
     */
    private static boolean checkRequireDescription(String keyword) {
        switch (keyword) {
        case"bye":
            //Fallthrough
        case"list":
            return false;
        default:
            return true;
        }
    }

    /**
     * Checks if the keyword requires a option.
     *
     * @param keyword , String to be checked.
     * @return boolean, if the keyword requires a option.
     */
    private static boolean checkRequireOption(String keyword) {
        switch (keyword) {
        case"done":
            //Fallthrough
        case"delete":
            //Fallthrough
        case "update":
            return true;
        default:
            return false;
        }
    }

    /**
     * Checks if the keyword requires a deadline.
     *
     * @param keyword , String to be checked.
     * @return boolean, if the keyword requires a deadline.
     */
    private static boolean checkRequireDeadline(String keyword) {
        switch(keyword) {
        case "find":
            //Fallthrough
        case "todo":
            return false;
        default:
            return true;
        }
    }

    /**
     * Returns a command that does not require a description.
     *
     * @param commandParts , String array containing the parts of the command (Keyword)
     * @return Command , a command based on the keyword.
     * @throws DukeException , if the keyword is not a accepted keyword.
     */
    private static Command createWithoutDescCommand(String[] commandParts) throws DukeException {
        String keyword = commandParts[0];
        if (keyword.equalsIgnoreCase("bye")) {
            return new SaveCommand(keyword);
        } else if (keyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else {
            throw new DukeException(ERROR_INVALID_COMMAND);
        }
    }

    /**
     * Checks that the command entered has a numeric option.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @throws DukeException , if the option does not exist or it is not a number.
     */
    private static void checkOption(String[] commandParts) throws DukeException {
        try {
            String remainderCommand = commandParts[1].trim();
            int nextSpace = remainderCommand.indexOf(" ");
            if (nextSpace == -1) {
                nextSpace = remainderCommand.length();
            }
            int option = Integer.parseInt(remainderCommand.substring(0, nextSpace)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_OPTION);
        }
    }

    /**
     * Creates a command that requires a option.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @return Command , a command that requires a option based on the commandParts given.
     * @throws DukeException , if option is invalid or invalid keyword.
     */
    private static Command createWithOptionCommand(String[] commandParts) throws DukeException {
        String keyword = commandParts[0];

        try {
            String remainderCommand = commandParts[1].trim();
            int nextSpace = remainderCommand.indexOf(" ");
            if (nextSpace == -1) {
                nextSpace = remainderCommand.length();
            }
            int option = Integer.parseInt(remainderCommand.substring(0, nextSpace)) - 1;
            switch (keyword) {
            case "done" :
                return new DoneCommand(option);
            case "delete":
                return new DeleteCommand(option);
            default:
                throw new DukeException(ERROR_INVALID_COMMAND);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_OPTION);
        }
    }

    /**
     * Creates Command that does not need a deadline.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @return Command , a command that does not requires a deadline based on the commandParts given.
     * @throws DukeException , if keyword is a invalid keyword.
     */
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

    /**
     * Creates a update command.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @return Command , a update command.
     * @throws DukeException , if there is missing information.
     */
    private static Command createUpdateCommand(String[] commandParts) throws DukeException {
        String remainderCommand = commandParts[1].trim();
        int firstSpace = remainderCommand.indexOf(" ");
        int option = Integer.parseInt(remainderCommand.substring(0, firstSpace)) - 1;
        int nextSpace = remainderCommand.indexOf(" ", firstSpace + 1);
        if (nextSpace == -1) {
            throw new DukeException(ERROR_INVALID_UPDATE_COMMAND);
        }
        String field = remainderCommand.substring(firstSpace, nextSpace);
        String value = remainderCommand.substring(nextSpace + 1);
        return new UpdateCommand(option, field, value);
    }

    /**
     * Creates a add Command.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @return Command , a AddCommand to add a task.
     * @throws DukeException , if keyword is invalid.
     * @throws DukeDeadlineException , if deadline is missing or deadline is incorrect format.
     */
    private static Command createAddCommand(String[] commandParts) throws DukeException, DukeDeadlineException {
        String keyword = commandParts[0];
        String remainderCommand = commandParts[1].trim();
        Task task = null;

        assert !keyword.trim().isEmpty();

        Task t = null;
        int firstSlash = remainderCommand.indexOf("/");

        if (firstSlash == -1) {
            throw new DukeDeadlineException(ERROR_EMPTY_DEADLINE);
        }

        int nextSpace = remainderCommand.indexOf(" ", firstSlash) + 1;
        String taskDescription = remainderCommand.substring(0, firstSlash);

        if (keyword.equalsIgnoreCase("deadline")) {
            LocalDateTime deadline = parseDate(remainderCommand.substring(nextSpace), ERROR_INVALID_DEADLINE_DATETIME);
            task = new Deadline(taskDescription, deadline);
        } else if (keyword.equalsIgnoreCase("event")) {
            LocalDateTime[] deadlines = parseDates(remainderCommand.substring(nextSpace), ERROR_INVALID_EVENT_DATETIME);
            task = new Event(taskDescription, deadlines[0], deadlines[1]);
        } else {
            throw new DukeException(ERROR_INVALID_COMMAND);
        }

        return new AddCommand(task);
    }

    /**
     * Checks commandParts has description.
     *
     * @param commandParts , String Array containing the parts of the command (keyword,remainder).
     * @param errorMessage , Error message to display if description is missing.
     * @throws DukeException , if description is missing.
     */
    private static void checkDescription(String[] commandParts, String errorMessage) throws DukeException {
        if (commandParts.length != 2) {
            throw new DukeException(errorMessage);
        }
    }

    /**
     * Returns a array of LocalDateTime object.
     *
     * @param data String that contains the date.
     * @param errorMessage  error message to display when error is occur during prasing to LocalDateTIme.
     * @return LocalDateTime[] , Array of localDateTime objects parsed from the data provided.
     * @throws DukeDeadlineException , Exception thrown when dateTime provided is not in the correct format.
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
            String dateTime = date + " " + timePeriod[i].trim();
            deadline[i] = parseDate(dateTime, errorMessage);
        }
        return deadline;
    }

    /**
     * Returns LocalDateTime object based on string given.
     *
     * @param date , String that contains the date in the format of yyyy-M-d hhmm.
     * @param errorMessage , error message to display when error is occur during prasing to LocalDateTIme.
     * @return LocalDateTime , LocalDateTime object parsed from the data provided.
     * @throws DukeDeadlineException , Exception thrown when the data provided is not of yyyy-M-dd Hmm format.
     */
    public static LocalDateTime parseDate(String date, String errorMessage) throws DukeDeadlineException {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-M-dd Hmm"));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new DukeDeadlineException(errorMessage);
        }
    }

    /**
     * Returns a Task Object based on the csvData provided.
     *
     * @param csvData (a row from the saved file).
     * @return Task , the result of parsing the text.
     */

    public static Task parseForText(String csvData) throws DukeDeadlineException, DukeException {
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
                    deadlines[i] = Parser.parseDate(deadlineArr[i], ERROR_LOAD_TASK_DATE);
                }
                task = new Event(taskArr[2], deadlines[0], deadlines[1]);
                break;
            case "D":
                LocalDateTime deadline = Parser.parseDate(taskArr[3], ERROR_LOAD_TASK_DATE);
                task = new Deadline(taskArr[2], deadline);
                break;
            default:
                throw new DukeException(ERROR_LOAD_INVALID);
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
