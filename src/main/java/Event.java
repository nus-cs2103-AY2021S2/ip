public class Event extends Task {

    private final String timing;

    public Event(String description, String timing) {
        super(description, TaskType.EVENT);
        this.timing = timing;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (at: ");
        output.append(this.timing);
        output.append(")");
        return output.toString();
    }

    public static Event parseEvent(String description) {
        String[] partitioned = description.split("/at");
        return new Event(partitioned[0].strip(), partitioned[1].strip());
    }
}
