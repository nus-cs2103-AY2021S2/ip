public class Event extends Task {
    private final String time;

    Event(String name, String time) {
        super(name);
        this.time = time;
    }

    String getTime() {
        return this.time;
    }

    String getSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.getTime());
    }
}