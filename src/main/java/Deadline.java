public class Deadline extends Task {

    protected String by;

    public Deadline(String description, int status, String by) {
        super(description,status);
        this.by = by;
    }

    @Override
    public String toTxt(){
        return "D |" + super.toString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
