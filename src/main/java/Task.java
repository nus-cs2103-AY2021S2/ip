import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task object for CS2103T iP. Stores relevant data regarding a task, including type, task,
 * date and time (if required) and whether it has been completed.
 */
public class Task {
    private final String task;
    private boolean done = false;
    private final int type;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a Task object.
     * @param task Description of task.
     * @param type Type of task: 0 is todo, 1 is deadline, 2 is event
     */

    public Task(String task, int type) {
        if (type == 0) {
            this.task = task;
            this.date = null;
            this.time = null;
        } else {
            String[] info = task.split("/");
            if (info.length == 1 || info[0].equals(" ")) {
                throw new IllegalArgumentException();
            } else if (type == 1) {
                this.date = LocalDate.parse(info[1].substring(3));
                this.time = null;
            } else {
                this.date = null;
                this.time = LocalTime.parse(info[1].substring(3));
            }
            this.task = info[0];
        }
        this.type = type;
    }

    /**
     * When called on a task, marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Checks for whether the task is done. Used as part of the toString() method.
     * @return "[ ]" for not done, "[X]" for done.
     */
    public String checkDone() {
        if (this.done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Checks for the task type. Used as part of the toString() method.
     * @return T for todo, D for deadline and E for event.
     */
    public String checkType() {
        if (this.type == 0) {
            return "[T]";
        } else if (this.type == 1) {
            return "[D]";
        } else {
            return "[E]";
        }
    }

    /**
     * A readable format of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (this.type == 0) {
            return checkType() + checkDone() + this.task;
        } else if  (this.type == 1) {
            return checkType() + checkDone() + this.task + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return checkType() + checkDone() + this.task + "(at: "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"))+ ")";
        }
    }

    /**
     * Generates a string that can be stored for import upon program start.
     * @return A simplified string representation of the task.
     */
    public String export() {
        String done = this.done ? " 1" : " 0";
        String deadline = this.type == 0 ? "" : this.type == 1 ? "/by " + this.date : "/at "
                + this.time;
        return this.type + done + this.task + deadline;
    }
}
