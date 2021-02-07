package mike.task;

public class FixedDurationTask extends Task {
    protected final String time;

    public FixedDurationTask(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + "(needs: " + this.time.toString() + ")";
    }
}
