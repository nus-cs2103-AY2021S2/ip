public class Deadline extends Task {

    private String deadline;

    /**
     * Constructor method.
     * @param description String that describes the task.
     * @param deadline String that describes the deadline.
     */
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overrides Task's toString method.
     * @return String output for the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
