package duke.task;

import duke.exceptions.EmptyTaskDukeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

/**
 * Deadline task for tasks that are due by a certain date
 */
public class Deadline extends Task {
    private LocalDate dueBy;
    private final static String DUE_COMMAND = "/by";

    public Deadline(String input) throws EmptyTaskDukeException, DateTimeParseException {
        super(getTaskNameFromInput(input));
        dueBy = LocalDate.parse(getDueDateFromInput(input));
    }

    public Deadline(String name, String dueBy) throws EmptyTaskDukeException {
        super(name);
        this.dueBy = LocalDate.parse(dueBy);
    }

    /**
     * Parses the task name from the input
     * @param input the input entered by the user
     * @return trimmed input of the task description
     */
    private static String getTaskNameFromInput(String input) {
        if (input.contains(DUE_COMMAND)) {
            return input.split(DUE_COMMAND)[0].trim();
        } else {
            return input;
        }
    }

    private static String getDueDateFromInput(String input) {
        if (input.contains(DUE_COMMAND)) {
            return removeStartingWhiteSpace(input.split(DUE_COMMAND)[1]);
        } else {
            return "not specified";
        }
    }

    private static String removeStartingWhiteSpace(String input) {
        if (input.substring(0, 1).equals(" ")) {
            return input.substring(1);
        }
        return input;
    }

    public String getDueBy() {
        return this.dueBy.toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[D]" + "[" + taskStringCheck + "] " + super.getTaskName()
                + " (by: " + formatDate(dueBy) + ")";
    }

    private String formatDate(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        Month mth = date.getMonth();
        String dateString = "";
        dateString = getDayString(day) + " " + getMthString(mth) + " "
                + date.getDayOfMonth() + " " + date.getYear();
        return dateString;
    }

    private String getDayString(DayOfWeek day) {
        switch (day) {
        case MONDAY:
            return "Mon";
        case TUESDAY:
            return "Tue";
        case WEDNESDAY:
            return "Wed";
        case THURSDAY:
            return "Thu";
        case FRIDAY:
            return "Fri";
        case SATURDAY:
            return "Sat";
        default:
            return "Sun";
        }
    }

    private String getMthString(Month mth) {
        switch (mth) {
        case JANUARY:
            return "Jan";
        case FEBRUARY:
            return "Feb";
        case MARCH:
            return "Mar";
        case APRIL:
            return "Apr";
        case MAY:
            return "May";
        case JUNE:
            return "Jun";
        case JULY:
            return "Jul";
        case AUGUST:
            return "Aug";
        case SEPTEMBER:
            return "Sep";
        case OCTOBER:
            return "Oct";
        case NOVEMBER:
            return "Nov";
        default:
            return "Dec";
        }
    }
}
