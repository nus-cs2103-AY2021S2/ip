public class Event extends Task {
    private String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getTime());
    }
}