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
        assert unformattedDate != null : "unformattedDate should not be null String";

        String[] dateArr = unformattedDate.split(" ");
        assert dateArr.length == 3 : "date should have only year, month and day when split on blank space";

        String monthString = String.format("%02d", Month.valueOf(dateArr[0].toUpperCase()).getValue());
        String day = String.format("%02d", Integer.parseInt(dateArr[1]));
        String year = dateArr[2];
        String formattedDate = year + "-" + monthString + "-" + day;
        return formattedDate;
    }

    /**
     * Processes a "todo" command and returns a ToDo object.
     *
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
     *
     * @param command command passed in by the user.
     * @return Deadline object.
     * @see Deadline
     */
    public Deadline parseAddDeadline(String command) {
        assert command.contains("/by ") : "command should have '/by ' for deadline Task";

        String[] deadlineAndTask = command.split(" /by ");
        return new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(9));
    }

    /**
     * Processes a "event" command and returns an Event object.
     *
     * @param command command passed in by the user.
     * @return Event object.
     * @see Event
     */
    public Event parseAddEvent(String command) {
        assert command.contains("/at ") : "command should have '/at ' for deadline Task";

        String[] eventTimeAndTask = command.split(" /at ");
        //offset of 6 to remove "event " frm statement
        return new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(6));
    }

    /**
     * Processes a "delete" command and returns the index of the Task to be deleted from Task List.
     *
     * @param command command passed in by the user.
     * @return Index of Task to be deleted.
     */
    public int parseDeleteCommand(String command) {
        assert command.length() > 0 && command.contains(" ") : "delete command should have 2 words";
        return Integer.parseInt(command.split(" ")[1]);
    }

    /**
     * Processes a "find" command and returns a TaskList object containing all Tasks that match the string searched.
     *
     * @param command  command passed in by the user.
     * @param userList TaskList of the user.
     * @return TaskList containing tasks for which the keywords are matched.
     */
    public TaskList parseFindCommand(String command, TaskList userList) {
        assert command.length() > 4 : "find command should have at least 5 characters";
        assert userList != null : "user's Task List used to find tasks with keywords should not be empty";

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

