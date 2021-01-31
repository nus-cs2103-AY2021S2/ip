package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Task (should be an abstract class but it is currently not) is a superclass
 * of Event, Deadline and Todo.
 * Duke manages these tasks.
 */
public class Task {
    protected String type = "";
    boolean isDone = false;
    String taskLine;
    String name;
    String dateTime;
    LocalDate date;

    /**
     * Constructor for Task that sets all the instance variables to a default state.
     * eg, name = "";
     *
     * @param taskLine untouched input from the user
     */
    protected Task(String taskLine) {
        this.taskLine = taskLine;
        this.name = "";
        this.dateTime = "";
        this.date = LocalDate.parse("0000-01-01");
    }

    protected String status() {
        String status = isDone ? "X" : "O";
        return status;
    }

    protected String printNew() {
        return toString();
    }

    protected String type() {
        return "task";
    }

    protected void setDateTimeLD(String time) {
        try {
            this.date = LocalDate.parse(time);
            this.dateTime = date.getMonth().name() + " " + date.getDayOfMonth() + " " + date.getYear();
        } catch (Exception e) {
        }
    }
}
