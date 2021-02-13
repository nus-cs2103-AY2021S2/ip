import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class Task to represent a Task
 */
public abstract class Task {
    /** Description of a class */
    private String description;
    /** Completion  Status of the task */
    private boolean isDone;

    /**
     * Constructor of the Task object.
     * @param desc Description of the task
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Marks a task a done if the task is not yet done.
     * @return boolean indicating if there is a change
     */
    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        //Return boolean to signal that we have successfully / fail to mark task as done.
        return false;
    }

    /**
     * Returns the task description.
     * @return String , String that contains the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns result of the updating of task.
     * @param field , field to be updated
     * @param value , value to be updated
     * @return String , result of the updating
     */
    public String update(String field, String value) {
        if (field.contains("desc")) {
            description = value;
            return Ui.showSuccessUpdate();
        }
        return Ui.showFailUpdate();
    }

    /**
     * Returns the representation of a task for saving.
     * @return String representation of a task for saving
     */
    public String save() {
        return String.format("%s,%s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns the representation of a task.
     * @return String representation of a task
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns a String of the datetime of the format "MMM d yyyy Hmm".
     * @param dateTime LocalDateTime object to be formatted
     * @return String , String that conatinas the LocalDateTIme object in the "MMM d yyyy Hmm" format
     */
    public String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy Hmm"));
    }

    /**
     * Returns a Strubg representation of dateTime for saving.
     * @param dateTime LocalDateTime object to be formatted
     * @return String, String that contains the LocalDateTime object in the "yyyy-M-d Hmm"
     */
    public String saveFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-d Hmm"));
    }

    /**
     * Returns a String representation of the status of the task.
     * @return String , String representation of the status of the task
     */
    public String getStatusIcon() {
        return (this.isDone) ? "X" : " ";
    }
}
