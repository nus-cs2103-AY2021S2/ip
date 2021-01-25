public class Deadlines extends Task{
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadlines(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String getEmptyDescError() {
        return "Oops! Description of deadline " + super.getEmptyDescError();
    }

    @Override
    public String formatData() {
        return "D | " + super.formatData() + " | " + by;
    }
}
