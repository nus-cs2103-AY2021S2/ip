package duke.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.SearchCommand;
import duke.exception.DukeException;
import duke.ui.UI;

/**
 * Parser class to handle all user commands and input.
 */
public class Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Invoke the respective command method to handle the different cases of user input
     * @param input user input
     * @return String message to be displayed upon completion of commands
     * @throws DukeException
     * @throws IOException
     */
    public static String parse(String input) throws DukeException, IOException {

        String[] commandArray = input.split("\\s+");
        String[] userInputArray = separateUserInput(input);
        String type = userInputArray[0];
        String taskDetail = input.replace(type, " ");

        assert commandArray[0] != null : "Command cannot be null";

        if (input.equals("bye")) {
            ExitCommand ec = new ExitCommand();
            return ec.execute();
        }

        if (commandArray[0].equals("find")) {
            validatingTaskDetails(taskDetail, "find");
            SearchCommand sc = new SearchCommand(taskDetail);
            return sc.execute();
        } else if (input.equals("list")) {
            ListCommand lc = new ListCommand(null);
            return lc.execute();
        } else if (commandArray[0].equals("delete")) {
            validatingTaskDetails(taskDetail, "delete");
            DeleteCommand dc = new DeleteCommand(commandArray[1]);
            return dc.execute();
        } else if (commandArray[0].equals("done")) {
            validatingTaskDetails(taskDetail, "done");
            DoneCommand doneCommand = new DoneCommand(commandArray[1]);
            return doneCommand.execute();
        } else {
            return invokeAddCommand(type, taskDetail, input);
        }
    }

    /**
     * Invoke and pass in the necessary information to the add command method
     * @param type type of task
     * @param userInput user input
     * @param taskDetail details of the task
     * @return String message to be displayed upon completion of commands
     * @throws DukeException
     * @throws IOException
     */
    public static String invokeAddCommand(String type, String userInput, String taskDetail) throws DukeException {
        switch (type) {
        case ("todo"):
            AddCommand ac = new AddCommand(userInput, "todo", null, null, null);
            if (userInput.isBlank()) {
                throw new DukeException(UI.displayInvalidParameter("noDescription"));
            }
            return ac.execute();

        case ("deadline"):
            if (userInput.isBlank()) {
                throw new DukeException(UI.displayInvalidParameter("noDescription"));
            }

            //check if there are no parameter following /by
            String[] dueDateArray = separateDueDate(taskDetail, "deadline");

            //check for format of given date
            LocalDate deadlineDueDate = check_valid_date(dueDateArray[1]);
            if (dueDateArray.length == 2) {
                throw new DukeException(UI.displayInvalidParameter("noDueTime"));
            } else if (dueDateArray.length > 3) {
                throw new DukeException(UI.displayDeadlineInvalidParameter("extraParameter"));
            }
            LocalTime deadlineDueTime = check_valid_time(dueDateArray[2]);

            String[] taskDescription = userInput.split("/by");
            AddCommand addCommand = new AddCommand(taskDescription[0], "deadline",
                        deadlineDueDate, deadlineDueTime, null);
            return addCommand.execute();
        case ("event"):
            LocalDate startDate = null;
            LocalTime startTime = null;
            LocalTime endTime = null;

            String[] eventDueDateArray = separateDueDate(taskDetail, "event");
            startDate = check_valid_date(eventDueDateArray[1]);

            if (eventDueDateArray.length == 2) {
                throw new DukeException(UI.displayInvalidParameter("noDueDate"));
            } else if (eventDueDateArray.length > 3) {
                throw new DukeException(UI.displayEventInvalidParameter("extraParameter"));
            }
            String[] eventDueTimeArray = separateStartEndTime(eventDueDateArray);

            try {
                startTime = LocalTime.parse(eventDueTimeArray[0], TIME_FORMATTER);
                endTime = LocalTime.parse(eventDueTimeArray[1], TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DukeException(UI.displayEventInvalidParameter("wrongTimeFormat"));
            }

            if (startTime.isAfter(endTime) || startTime.toString().equals(endTime.toString())) {
                throw new DukeException(UI.displayEventInvalidParameter("invalidTimeInput"));
            }

            String[] description = userInput.split("/at");

            if (description.length > 2) {
                throw new DukeException(UI.displayInvalidParameter("lesserParameterGiven"));
            }

            AddCommand addEventCommand = new AddCommand(description[0], "event", startDate, startTime, endTime);
            return addEventCommand.execute();

        default:
            throw new DukeException(UI.displayUnknownCommand());
        }
    }

    /**
     * Returns an array of string after separating the given input by spaces
     * @param userInput
     * @return array of string that is split
     * @throws DukeException
     */
    public static String[] separateUserInput(String userInput) throws DukeException {
        String[] inputArray = userInput.trim().split(" ");
        String taskName = inputArray[0];

        if (taskName.isBlank()) {
            throw new DukeException(UI.displayInvalidParameter("noTodoDescription"));
        } else {
            return inputArray;
        }
    }

    /**
     * Returns the array of string after separating task details by due dates
     * @param taskDetail detail of task
     * @param taskType type of task
     * @return array of string containing due date and due time
     * @throws DukeException
     */
    public static String[] separateDueDate(String taskDetail, String taskType) throws DukeException {

        if (taskDetail.contains("/by") && taskType.equals("deadline")) {
            String[] dueBy = splitParametersByDelimiter(taskDetail, "/by", taskType);
            String[] dueTimeArray = splitParametersByDelimiter(dueBy[1], " ", taskType);
            return dueTimeArray;
        } else if (taskDetail.contains("/at") && taskType.equals("event")) {
            String[] dueDetailsArray = splitParametersByDelimiter(taskDetail, "/at", taskType);
            String[] dueDateAndDueTimeArray = splitParametersByDelimiter(dueDetailsArray[1], " ", taskType);
            return dueDateAndDueTimeArray;
        } else {
            throw new DukeException(UI.displayInvalidParameter("wrongCommand"));
        }
    }

    /**
     * Returns the array of string after separating task details by start and end time
     * @param taskDueTime task details regarding the start and end time
     * @return array of string containing start and end time
     * @throws DukeException
     */
    public static String[] separateStartEndTime(String[] taskDueTime) throws DukeException {
        String[] timeArr;
        try {
            if (taskDueTime[2].contains("-")) {
                timeArr = taskDueTime[2].split("-");
            } else {
                throw new DukeException(UI.displayEventInvalidParameter("errorSeparatingTime"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(UI.displayEventInvalidParameter("extraParameter"));
        }
        return timeArr;
    }

    /**
     * Returns the array of string after separating task details by the given delimiter with handled exception
     * @param givenArray array used to separate inputs
     * @param delimiter string to separate the given array by
     * @param taskType type of task
     * @throws DukeException
     */
    public static String[] splitParametersByDelimiter(String givenArray, String delimiter, String taskType)
            throws DukeException {

        String[] separateOutput;
        try {
            separateOutput = givenArray.split(delimiter);
            String output = separateOutput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            if (taskType.equals("deadline")) {
                throw new DukeException(UI.displayDeadlineInvalidParameter("noDueDateOrWrongFormat"));
            } else {
                throw new DukeException(UI.displayEventInvalidParameter("noDueDateOrWrongFormat"));
            }
        }

        boolean isThereTaskDescriptionForDeadline = taskType.equals("deadline") && delimiter.equals("/by")
                && separateOutput[0].trim().replace("deadline", "").isEmpty();

        boolean isThereTaskDescriptionForEvent = taskType.equals("event") && delimiter.equals("/at")
                && separateOutput[0].trim().replace("event", "").isEmpty();

        if (isThereTaskDescriptionForDeadline || isThereTaskDescriptionForEvent) {
            throw new DukeException(UI.displayInvalidParameter("noDescription"));
        }

        return separateOutput;
    }

    /**
     * Check the validity of task parameters
     * @param taskDetail
     * @throws DukeException
     */
    public static void validatingTaskDetails(String taskDetail, String command) throws DukeException {
        if (taskDetail.isBlank()) {
            throw new DukeException(UI.displayInvalidParameter(command));
        } else if (command.equals("done") || command.equals("delete")) {
            try {
                Integer.parseInt(taskDetail.trim());
            } catch (NumberFormatException e) {
                throw new DukeException(UI.displayInvalidParameter(command));
            }

        }
    }

    /**
     * Check the validity of the date if it has already passed or is an invalid date
     * @param givenDate
     * @throws DukeException
     */
    public static LocalDate check_valid_date(String givenDate) throws DukeException {
        LocalDate dueDate;
        String dayOfMonth;
        try {
            dueDate = LocalDate.parse(givenDate, DATE_TIME_FORMATTER);
            dayOfMonth = givenDate.substring(0, 2);
        } catch (DateTimeParseException e) {
            throw new DukeException(UI.displayInvalidParameter("invalidDate"));
        }

        if (Integer.parseInt(dayOfMonth) > dueDate.lengthOfMonth()) {
            throw new DukeException(UI.displayInvalidParameter("dateExtendTotalDateInMonth"));
        }

        LocalDate today = LocalDate.now();
        if (dueDate.isBefore(today)) {
            throw new DukeException(UI.displayInvalidParameter("invalidDate"));
        }
        return dueDate;
    }

    /**
     * Check the validity of the given time
     * @param givenTime string containing the start and end time
     * @throws DukeException
     */
    public static LocalTime check_valid_time(String givenTime) throws DukeException {
        LocalTime dueTime;
        try {
            dueTime = LocalTime.parse(givenTime, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(UI.displayInvalidParameter("invalidTimeFormat"));
        }
        return dueTime;
    }
}
