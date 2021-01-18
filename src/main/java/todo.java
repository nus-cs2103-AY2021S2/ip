public class todo extends Task {
    protected String description;
    protected boolean done;

    todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
