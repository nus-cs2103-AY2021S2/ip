import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDate dueAt;

    private static final String DUE_COMMAND = "/at";

    public Events(String input) throws EmptyTaskDukeException, DateTimeParseException {
        super(getTaskNameFromInput(input));
        dueAt = LocalDate.parse(getDueDateFromInput(input));
        Task.incrementNumOfTask();
    }

    public Events(String name, String dueAt) throws EmptyTaskDukeException {
        super(name);
        this.dueAt = LocalDate.parse(dueAt);
    }

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

    public String getDueAt() {
        return this.dueAt.toString();
    }

    @Override
    public String setDone() {
        super.setTaskCompleted();
        return "Nice! I've marked this event as done:\n" +
                toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[E]" + "[" + taskStringCheck + "] " + super.getTaskName()
                + "(at: " + formatDate(dueAt) +  ")";
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
