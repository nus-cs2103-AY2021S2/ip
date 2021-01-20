public class Event extends Task {

    private String start;
    private String end;

    public Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s-%s)", super.toString(), this.getStart(), this.getEnd());
    }

}
