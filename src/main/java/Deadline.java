
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
        String result = "[D]" + super.toString() + " (by: " + by.toFormattedString() + ")";
        System.out.println(priority);
        return priority != null
                ? result + " " + "[" + priority.toString() + "]"
                : result;
    }

}
