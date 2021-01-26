public class Deadline extends Task {

    /**
     * Stores the date this event will be due by.
     */
    protected String by;

    /**
     * Initializes a newly created deadline-task object with a description and the date.
     * @param description Description of the task
     * @param by Date of the task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String saveTask() { return "D | " + super.saveTask() + " | " + this.by; }

    /**
     * Converts this object to a string that represents the deadline-task
     * @return A string representing whether the deadline-task is done and the deadline-task description with the date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + by + ")";
    }
}
