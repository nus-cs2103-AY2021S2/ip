package duke;

import java.time.Month;
import java.util.ArrayList;

/**
 * Represents a parser that makes sense of user input by reformatting data, making objects etc.
 * Processes commands and returns parsed information
 */
public class Parser {

    /**
     * Returns formattedDate in "yyyy-MM-DD" format from unformatted "month day yyyy".
     *
     * @param unformattedDate String in "month day year" format.
     * @return formatted date in "yyyy-MM-DD" format.
     */
    public String parseDate(String unformattedDate) {
        try {
            String[] dateArr = unformattedDate.split(" ");
            String monthString = String.format("%02d", Month.valueOf(dateArr[0].toUpperCase()).getValue());
            String day = String.format("%02d", Integer.parseInt(dateArr[1]));
            String year = dateArr[2];
            String formattedDate = year + "-" + monthString + "-" + day;
            return formattedDate;
        } catch (IllegalArgumentException e) {
            return e.getMessage() + " date input as 'month day year' format ";
        }
    }

    /**
     * Processes a "todo" command and returns a ToDo object.
     *
     * @param command command passed in by the user.
     * @return ToDo object.
     * @throws InvalidToDoCommandException if todo command used incorrectly.
     * @see Deadline
     */
    public ToDo parseAddTodo(String command) throws InvalidToDoCommandException {
        String toDoTaskDetail = command.substring(5);
        if (toDoTaskDetail.length() == 0 || toDoTaskDetail.startsWith(" ")) {
            throw new InvalidToDoCommandException();
        } else {
            ToDo newTodo = new ToDo(toDoTaskDetail);
            return newTodo;
        }
    }

    /**
     * Processes a "deadline" command and returns a Deadline object.
     *
     * @param command command passed in by the user.
     * @return Deadline object.
     * @throws InvalidDeadlineCommandException if deadline command used incorrectly.
     * @see Deadline
     */
    public Deadline parseAddDeadline(String command) throws InvalidDeadlineCommandException {
        if (!command.contains("  /by ")) {
            throw new InvalidDeadlineCommandException();
        } else {
            String[] deadlineAndTask = command.split(" /by ");
            int deadlineDetailStartIndexOffset = 9;
            return new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(deadlineDetailStartIndexOffset));
        }
    }

    /**
     * Processes a "event" command and returns an Event object.
     *
     * @param command command passed in by the user.
     * @return Event object.
     * @throws InvalidEventCommandException if event command used incorrectly.
     * @see Event
     */
    public Event parseAddEvent(String command) throws InvalidEventCommandException {
        if (!command.contains(" /at ")) {
            throw new InvalidEventCommandException();
        } else {
            String[] eventTimeAndTask = command.split(" /at ");
            int eventDetailStartIndexOffset = 6;
            return new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(eventDetailStartIndexOffset));
        }
    }

    /**
     * Processes a "delete" command and returns the index of the Task to be deleted from Task List.
     *
     * @param command command passed in by the user.
     * @return Index of Task to be deleted.
     */
    public int parseDeleteCommand(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    /**
     * Processes a "find" command and returns a TaskList object containing all Tasks that match the string searched.
     *
     * @param command  command passed in by the user.
     * @param userList TaskList of the user.
     * @return TaskList containing tasks for which the keywords are matched.
     * @throws InvalidFindCommandException if find command in incorrect format.
     */
    public TaskList parseFindCommand(String command, TaskList userList) throws InvalidFindCommandException {
        if (command.length() < 5) {
            throw new InvalidFindCommandException();
        } else {
            String keywords = command.substring(5);
            ArrayList<Task> results = new ArrayList<>();
            for (Task task : userList.getTaskList()) {
                if (task.getTaskDetail().contains(keywords)) {
                    results.add(task);
                }
            }
            return new TaskList(results);
        }
    }
}
