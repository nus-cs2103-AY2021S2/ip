public class Deadlines extends Task {
    private final String TYPE_ICON = "[D]";

    private String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        return String.format("%s(by:%s)", this.description, this.by);
    }

    @Override
    public String toString() {
        return String.format("%s(by:%s)", this.description, this.by);
    }

}
