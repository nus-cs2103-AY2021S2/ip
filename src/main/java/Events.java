public class Events extends Task {
    private final String TYPE_ICON = "[E]";

    private String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTypeIcon() {
        return this.TYPE_ICON;
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.at);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", this.description, this.at);
    }
}
