package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
/**
 * Represents a task. A <code>Task</code> consist of
 * name, type (todo, event, deadline), status and deadline (if applicable).
 */
public class Task {
    private String name;
    private boolean isDone;
    private String type;
    private String preposition;
    private LocalDate date;

    /**
     * Task constructor for todo.
     *
     * @param type
     * @param name
     */
    public Task(String type, String name) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = null;
    }

    /**
     * Task constructor for event/deadline.
     *
     * @param type
     * @param name
     * @param date
     * @param preposition
     */
    public Task(String type, String name, String date, String preposition) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = LocalDate.parse(date);
        this.preposition = preposition;
    }

    /**
     * Getter method for type.
     *
     * @return the type of the <code>Task</code>
     */
    public String getType() {
        return type;
    }

    /**
     * Getter method for status.
     *
     * @return the status of the <code>Task</code>
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Getter method for date.
     *
     * @return the date of the <code>Task</code>
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter method for name.
     *
     * @return the name of the <code>Task</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for status.
     * This method set the <code>Task</code> status to done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a readable string representing the task that can
     * be used for outputting to user.
     *
     * @return a representative string of <code>Task</code>
     */
    @Override
    public String toString() {
        if (type.equals("E") || type.equals("D")) {
            return String.format("[%s][%s] %s (%s: %s)",
                    type,
                    isDone ? "X" : " ",
                    name,
                    preposition,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            return String.format("[%s][%s] %s",
                    type,
                    isDone ? "X" : " ",
                    name);
        }
    }

    /**
     * Returns a formatted representative of <code>Task</code>.
     * Fields of <code>Task</code> will be formatted with delimiters for
     * writing to text file.
     *
     * @return a string that is formatted for writing
     */
    public String toSaveFormat() {
        String line = type + " | " + (isDone ? "1" : "0") + " | " + name;
        if (type.equals("E") || type.equals("D")) {
            line += " | " + date + " | " + preposition;
        }
        return line;
    }

    /**
     * Create and return a <code>Task</code>.
     * This method is used for creating a task (todo/event/deadline).
     * If the task is an event or a deadline, user will input the date and preposition accordingly,
     * else input empty string.
     *
     * @param taskName the name of the task
     * @param type the type of the task
     * @param date the date of the task (if applicable)
     * @param preposition the preposition for the task (if applicable)
     * @return the Task created
     * @throws DukeException
     */
    public static Task createTask(String taskName, String type, String date, String preposition) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
        if (type.equals("todo")) {
            return new Task("T", taskName);
        } else if (type.equals("event")) {
            return new Task("E", taskName, date, preposition);
        } else if (type.equals("deadline")) {
            return new Task("D", taskName, date, preposition);
        } else {
            throw new DukeException("☹ OOPS!!! Tried to add wrong task type!");
        }
    }
}
