import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Task object for CS2103T iP. Stores relevant data regarding a task, including type, task,
 * date and time (if required) and whether it has been completed.
 */
public class Task {
    private final String task;
    private boolean isDone = false;
    private final int type;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a Task object.
     * @param task Description of task.
     * @param type Type of task: 0 is todo, 1 is deadline, 2 is event.
     */

    public Task(String task, int type) {
        assert type < 3 && type > -1;
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
        this.isDone = true;
    }

    /**
     * Checks for whether the task is done. Used as part of the toString() method.
     * @return "[ ]" for not done, "[X]" for done.
     */
    public String checkDone() {
        if (this.isDone) {
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

    public void changeTimeTo(long duration) {
        switch (this.type) {
            case 1:
                this.date = this.date.plusDays(duration);
                break;
            case 2:
                this.time = this.time.plusMinutes(duration);
                break;
            default:
        }
    }

    /**
     * A readable format of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (this.type == 0) {
            return checkType() + checkDone() + this.task;
        } else if (this.type == 1) {
            return checkType() + checkDone() + this.task + "(by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return checkType() + checkDone() + this.task + "(at: "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
        }
    }

    /**
     * Generates a string that can be stored for import upon program start.
     * @return A simplified string representation of the task.
     */
    public String export() {
        String done = this.isDone ? " 1" : " 0";
        String deadline = "";
        switch (this.type) {
            case 1:
                deadline += "/by " + this.date;
                break;
            case 2:
                deadline += "/at " + this.time;
                break;
            default:
        }
        return this.type + done + this.task + deadline;
    }
}
