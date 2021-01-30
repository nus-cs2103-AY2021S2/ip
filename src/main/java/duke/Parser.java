package duke;

import duke.Task;
import duke.ToDo;
import duke.Deadline;
import duke.Event;
import java.time.Month;

/**
 * Represents a parser that makes sense of user input by reformatting data, making objects etc.
 * Processes commands and returns parsed information
 */
public class Parser {
    /**
     * Returns formattedDate in "yyyy-MM-DD" format from unformatted "month day yyyy".
     * @param unformattedDate String in "month day year" format.
     * @return formatted date in "yyyy-MM-DD" format.
     */
    public String parseDate(String unformattedDate) {
        String[] dateArr = unformattedDate.split(" ");
        String monthString = String.format("%02d", Month.valueOf(dateArr[0].toUpperCase()).getValue());
        String day = String.format("%02d", Integer.parseInt(dateArr[1]));
        String year = dateArr[2];
        String formattedDate = year + "-" + monthString + "-" + day;
        return formattedDate;
    }

    /**
     * Processes a "todo" command and returns a ToDo object.
     * @param command command passed in by the user.
     * @return ToDo object.
     * @see Deadline
     */
    public ToDo parseAddTodo(String command) {
        System.out.println(command.substring(5));
        ToDo newTodo = new ToDo(command.substring(5));
        return newTodo;
    }

    /**
     * Processes a "deadline" command and returns a Deadline object.
     * @param command command passed in by the user.
     * @return Deadline object.
     * @see Deadline
     */
    public Deadline parseAddDeadline(String command) {
        String[] deadlineAndTask = command.split(" /by ");
        return new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(9));
    }

    /**
     * Processes a "event" command and returns an Event object.
     * @param command command passed in by the user.
     * @return Event object.
     * @see Event
     */
    public Event parseAddEvent(String command) {
        String[] eventTimeAndTask = command.split(" /at ");
        //offset of 6 to remove "event " frm statement
        return new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(6));
    }

    /**
     * Processes a "delete" command and returns the index of the Task to be deleted from Task List.
     * @param command command passed in by the user.
     * @return Index of Task to be deleted.
     */
    public int parseDeleteCommand(String command) {
        return Integer.parseInt(command.split("")[1]);
    }
}

