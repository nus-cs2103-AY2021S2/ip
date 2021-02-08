package bob.processor;

import bob.BobException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

/**
 * Makes sense of the user command
 */
public class Parser {

    /**
     * Returns a valid integer given a user's input
     *
     * @param userInput User's full input
     * @return An integer that is the index of the tasks which the user is done with or wants to remove
     * @throws BobException if no integer was indicated or integer was invalid
     */
    public int parseNumber(String userInput) throws BobException {
        int number;
        try {
            String indexString = userInput.split(" ")[1];
            number = Integer.parseInt(indexString);
        } catch (PatternSyntaxException e) {
            throw new BobException("Please try again with a spacing before the number");
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("Remember to specify which task you are done with "
                    + "using a valid number", e);
        } catch (NumberFormatException e) {
            throw new BobException("Invalid format, please try again using numbers only.", e);
        }
        return number;
    }

    /**
     * Returns a string representing the task name given the user's input
     *
     * @param userInput User's full input
     * @return A string representing the task name
     */
    public String parseName(String userInput) throws BobException {
        try {
            int startIndex = userInput.indexOf(" ") + 1;
            int endIndex = userInput.indexOf("/");
            String name;
            if (startIndex == 0) {
                throw new BobException("No name detected. Please try again.");
            }
            if (endIndex == -1) {
                name = userInput.substring(startIndex);
            } else {
                name = userInput.substring(startIndex, endIndex - 1);
            }
            return name;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("No name detected. Please try again.", e);
        }
    }

    /**
     * Returns the date and time of the task given the user's input
     *
     * @param userInput User's full input
     * @return A LocalDateTime object that corresponds to the user input
     * @throws BobException if user input do not include date or time, or
     * invalid format of date and time given
     */
    public LocalDateTime parseDateTime(String userInput, String taskType) throws BobException {
        try {
            String dateTimeIndicator = "";
            if (taskType.equals("event")) {
                dateTimeIndicator = "/at: ";
            } else if (taskType.equals("deadline")) {
                dateTimeIndicator = "/by: ";
            }
            String[] taskDetails = userInput.split(dateTimeIndicator, 2);
            String dateTimeString = taskDetails[1];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeString, dateFormatter);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("The description of an event/deadline includes date and time.", e);
        } catch (DateTimeParseException e) {
            throw new BobException("Please enter the date and time in the proper format: yyyy-mm-dd hhmm", e);
        }
    }

    public Task parseLine(String fileLine) {
        String[] nextTaskDetails = fileLine.split(" \\| ", 0);
        String taskType = nextTaskDetails[0];
        String status = nextTaskDetails[1];
        String taskName = nextTaskDetails[2];

        boolean done = false;
        if (status.equals("1")) {
            done = true;
        }
        if (taskType.equals("T")) {
            return new Todo(taskName, done);
        } else {
            String dateTimeString = nextTaskDetails[3];
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateFormatter);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            if (taskType.equals("E")) {
                return new Event(taskName, done, date, time);
            } else {
                assert taskType.equals("D");
                return new Deadline(taskName, done, date, time);
            }
        }
    }
}
