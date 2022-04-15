
public class Deadline extends Task {
    protected Date by;
    protected String time;

    /**
     * Creates a Deadline object.
     *
     * @param description A description of the task.
     * @param by Date of when it is due by.
     * @param type String stating what type of task it is.
     */
    public Deadline(String description, Date by, String type) {
        super(description, type);
        this.by = by;
    }
    /**
     * Creates a Deadline object.
     *
     * @param description A description of the task.
     * @param by Date of when it is due by.
     * @param time String stating the time for the deadline.
     * @param type String stating what type of task it is.
     */
    public Deadline(String description, Date by, String time, String type) {
        super(description, type);
        this.by = by;
        this.time = time;
    }


    /**
     * Displays the deadline details and whether it is completed.
     *
     * @return String showing the information regarding the Deadline object.
     */
    @Override
    public String toString() {
        String result = "[D]" + super.toString() + " (by: " + by.toFormattedString();
        if (time == null) {
            result = result + ")";
        } else {
            result = result +  " " + time + ")";
        }
        return priority != null
                ? result + " " + "[" + priority.toString() + "]"
                : result;
    }

}
