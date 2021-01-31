package duke.parser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.SearchCommand;
import duke.data.DataStorage;
import duke.exception.DukeException;

/**
 * A class to handle all user input
 */
public class Parser {
    //  private static DataStorage storage = new DataStorage();
  //  private static TaskList taskList = new TaskList();

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    //Global Variable
    private static String taskName;


    /** Call onto the respective command classes to handle different cases of command
     * @param list
     * @param input
     * @return boolean
     * @throws DukeException
     */
    public static String parse(TaskList list, String input) throws DukeException, IOException {

        if (input.equals("bye")) {
            ExitCommand ec = new ExitCommand();
            return ec.toString();
        }
        String [] commandArray = input.split("\\s+");

        String [] userInputArray = separateUserInput(input);
        String type = userInputArray[0];
        String userInput = input.replace(type, " ");

        if (commandArray[0].equals("find")) {
            if (userInput.isBlank()) {
                throw new DukeException("Please input task description to be searched.");
            } else {
                SearchCommand sc = new SearchCommand(userInput);
                return sc.execute();
            }
        }
        else if (commandArray[0].equals("list")) {
            ListCommand lc = new ListCommand(null);
            return lc.execute();
        } else if (commandArray[0].equals("delete")) {
            DeleteCommand dc = new DeleteCommand(commandArray[1]);
            return dc.execute();
        } else if (commandArray[0].equals("done")) {
            DoneCommand doneCommand = new DoneCommand(commandArray[1]);
             return doneCommand.execute();
        } else {

            switch (type) {
            case ("todo"):
                AddCommand ac = new AddCommand(userInput, "todo", null,null,null);

                if (userInput.isBlank()) {
                    throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
                }
                return ac.execute();

            case ("deadline"):

                String [] dateTime = separateDueDate(input, "deadline");

                LocalDate date = LocalDate.parse(dateTime[0], dateFormatter);
                LocalTime time;
                if (dateTimeParse(dateTime, "deadline")) {
                    time = null;
                } else {
                    time = LocalTime.parse(dateTime[1], timeFormatter);
                }

                AddCommand addc = new AddCommand(userInput, "deadline", date,time,null);
                return addc.execute();

            case ("event"):

                String []dateTimeArray = separateDueDate(input, "event");
                String [] timeArray = separateStartEndTime(dateTimeArray);

                LocalDate startDate = LocalDate.parse(dateTimeArray[0], dateFormatter);
                LocalTime startTime = LocalTime.parse(timeArray[0], timeFormatter);
                LocalTime endTime = LocalTime.parse(timeArray[1], timeFormatter);

                AddCommand addCommand = new AddCommand(taskName, "events", startDate, startTime, endTime);
                return addCommand.execute();

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }


    /** Check if index is valid in array list
     * @param no index in arraylist
     * @param type event type
     * @param arraySize
     * @return boolean
     */
    public static boolean isValidTaskNumber(int no, String type, int arraySize) {
        try {
            if (no < 0 || no >= arraySize && !type.equals("done")) {
                throw new DukeException("There are no task in list. Please add some task and try again.");
            } else if (no >= arraySize && type.equals("delete")) {
                throw new DukeException("Item number " + no + " is not found in list. Please check again");
            } else if (arraySize < 0 && type.equals("list")) {
                throw new DukeException("There are no task in list. Please check again");
            } else {
                return true;
            }
        } catch (DukeException e) {
            e.printMessage();
            return false;
        }
    }

    /** Return array of string separated by space
     * @param userInput
     * @return array of string
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

    /** Return array of string separated into due dates
     * @param input
     * @param type
     * @return array of string
     * @throws DukeException
     */
    public static String[] separateDueDate (String input, String type) throws DukeException {
        if (input.contains("/by") && type.equals("deadline")) {
            String [] dueBy = input.split("/by ");
            taskName = dueBy[0].replace(type, "");
            String[] stringTime = dueBy[1].split(" ");
            return stringTime;
        } else if (input.contains("/at") && type.equals("event")) {
            String[] dueDetails = input.split("/at ");
            taskName = dueDetails[0].replace(type, "");
            String[] dateTime = dueDetails[1].split(" ");
            return dateTime;
        } else {
            throw new DukeException("OOPS!!! Wrong command to add deadlines or start/end time. "
                    + "Use /by for deadline and /at for event");
        }
    }

    /** Return array of string separated into start and end time
     * @param dateTime
     * @return array of string
     * @throws DukeException
     */

    public static String[] separateStartEndTime(String[] dateTime) throws DukeException {
        String[] timeArr;
        if (dateTime[1].contains("-")) {
            timeArr = dateTime[1].split("-");
        } else {
            throw new DukeException("Please seperate the time with '-'. For ie, 1800-2000 or include start/end date");
        }
        return timeArr;
    }

    /** Check if user passed in enugh parameters for deadline and event task
     * @param dateTime
     * @param type
     * @return boolean
     * @throws DukeException
     */
    public static boolean dateTimeParse(String[] dateTime, String type) throws DukeException {
        if (dateTime.length < 2 && type.equals("deadline")) {
            return true;
        } else if (dateTime.length > 2 && type.equals("deadline")) {
            throw new DukeException("OOPS!!! Deadline task only accept one start time. Please create an event instead");
        }
        return false;
    }
}
