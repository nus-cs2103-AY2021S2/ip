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

/**
 * Parser class to handle all user commands and input.
 */
public class Parser {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

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
            isTaskDetailEmpty(taskDetail, "Please input task description to be searched.");
            SearchCommand sc = new SearchCommand(taskDetail);
            return sc.execute();
        } else if (input.equals("list")) {
            ListCommand lc = new ListCommand(null);
            return lc.execute();
        } else if (commandArray[0].equals("delete")) {
            isTaskDetailEmpty(taskDetail, "Please input task description to be searched.");
            DeleteCommand dc = new DeleteCommand(commandArray[1]);
            return dc.execute();
        } else if (commandArray[0].equals("done")) {
            isTaskDetailEmpty(taskDetail, "You did not specify a task index. "
                    + "Please try again with a valid task index.");
            DoneCommand doneCommand = new DoneCommand(commandArray[1]);
            return doneCommand.execute();
        } else {
            return invokeAddCommand(type, taskDetail, input);
        }
    }


    /**
     * Invoke and pass in necessary information to the add command method
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
                    throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
                }
                return ac.execute();

            case ("deadline"):
                LocalDate deadlineDueDate = null;
                LocalTime deadlineDueTime;
                String[] dueDateArray = separateDueDate(taskDetail, "deadline");

                try {
                    deadlineDueDate = LocalDate.parse(dueDateArray[0], dateFormatter);
                } catch (DateTimeParseException e) {
                    throw new DukeException("This is an invalid date. Please try again with the expected date format"
                            + " given as: /by (YYYY-MM-DD hhmm).");
                }

                if (dueDateArray.length < 2) {
                    throw new DukeException("OOPS!!! Please follow the given format to add deadline. "
                            + "deadline task description /by (YYYY-MM-DD HHMM)");
                } else if (dueDateArray.length > 2) {
                    throw new DukeException("OOPS!!! You might not be following the syntax given or "
                            + "trying to add more than one task at a time.");
                } else {
                    try {
                        deadlineDueTime = LocalTime.parse(dueDateArray[1], timeFormatter);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("This is an invalid time format. "
                                + "Please try again with the expected time format"
                                + " given as: HHMM with no spacing or semi colon.");
                    }
                }

                String[] taskDescription = userInput.split("/by");
                AddCommand addCommand = new AddCommand(taskDescription[0], "deadline",
                        deadlineDueDate, deadlineDueTime, null);
                return addCommand.execute();

            case ("event"):
                LocalDate startDate = null;
                LocalTime startTime = null;
                LocalTime endTime = null;

                String[] eventDueDateArray = separateDueDate(taskDetail, "event");
                String[] eventDueTimeArray = separateStartEndTime(eventDueDateArray);

                try {
                    startDate = LocalDate.parse(eventDueDateArray[0], dateFormatter);
                    startTime = LocalTime.parse(eventDueTimeArray[0], timeFormatter);
                    endTime = LocalTime.parse(eventDueTimeArray[1], timeFormatter);
                } catch (DateTimeParseException e) {
                    throw new DukeException("This is an invalid date. Please try again with the expected date format"
                            + " given as: /at (YYYY-MM-DD hhmm-hhmm).");
                }

                String[] description = userInput.split("/at");

                if (description.length > 2) {
                    throw new DukeException("OOPS!!! You might not be following the syntax given or "
                            + "trying to add more than one task at a time.");
                }

                AddCommand addEventCommand = new AddCommand(description[0], "event", startDate, startTime, endTime);
                return addEventCommand.execute();

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Check if task parameter is empty
     * @param taskDetail
     * @param message message to be printed
     * @throws DukeException
     */
    public static void isTaskDetailEmpty(String taskDetail, String message) throws DukeException {
        if (taskDetail.isBlank()) {
            throw new DukeException(message);
        }
    }

    /**
     * Returns an array of string after separating the given input by spaces
     * @param userInput
     * @return array of string that is split
     * @throws DukeException
     */
    public static String[] separateUserInput(String userInput) throws DukeException {
        String[] inputArray = userInput.split(" ");

        String taskName = inputArray[0];

        if (taskName.isBlank()) {
            throw new DukeException("OOPS!!! The description of a " + inputArray[1] + " cannot be empty.");
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
            String[] dueBy = taskDetail.split("/by ");
            String[] stringTime = dueBy[1].split(" ");
            return stringTime;
        } else if (taskDetail.contains("/at") && taskType.equals("event")) {
            String[] dueDetailsArray = taskDetail.split("/at ");
            String[] dueDateAndDueTimeArray = dueDetailsArray[1].split(" ");
            return dueDateAndDueTimeArray;
        } else {
            throw new DukeException("OOPS!!! Wrong command to add deadlines or start/end time. "
                    + "Use /by for deadline and /at for event");
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
        if (taskDueTime[1].contains("-")) {
            timeArr = taskDueTime[1].split("-");
        } else {
            throw new DukeException("Please separate the time with '-'. For ie, 1800-2000 or include start/end date");
        }
        return timeArr;
    }
}