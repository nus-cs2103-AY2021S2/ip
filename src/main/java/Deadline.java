import java.time.LocalDateTime;

/** Class Deadline that represent a task with a deadline **/
public class Deadline extends Task {
    /** Symbol to represent a deadline task **/
    private static final char SYMBOL = 'D';
    /** deadline of the task **/
    private LocalDateTime deadline;

    /**
     * Constructor of a Deadline task.
     * @param desc Description of the deadline task
     * @param deadline Deadline of the deadline task
     */
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }

    /**
     * Returns the representation of a task.
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%c] %s (by: %s)", SYMBOL, super.toString(), super.format(this.deadline));
    }

    /**
     * Returns the representation of a task for saving.
     * @return String representation of a task for saving
     */
    @Override
    public String save() {
        return String.format("%c,%s,%s\n", SYMBOL, super.save(), super.saveFormat(this.deadline));
    }

    /**
     * Returns result of the updating of task.
     * @param field , field to be updated
     * @param value , value to be updated
     * @return String , result of the updating
     */
    public String update(String field, String value) {
        System.out.println(field);
        System.out.println(field.contains("deadline"));
        if (field.contains("desc")) {
            super.update(field, value);
        }

        try {
            LocalDateTime dateTime = Parser.parseDate(value, Parser.ERROR_INVALID_DATE_FORMAT);
            if (field.contains("deadline")) {
                deadline = dateTime;
                return Ui.showSuccessUpdate();
            }
        } catch (DukeDeadlineException e) {
            return Ui.showError(e.getMessage());
        }
        return Ui.showFailUpdate();
    }
}
