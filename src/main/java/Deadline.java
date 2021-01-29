

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by, String type) {
        super(description, type);
        this.by = by;
    }


    /**
     * displays the deadline details and whether it is completed.
     *
     * @return String showing the information regarding the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toFormattedString() + ")";
    }

}
