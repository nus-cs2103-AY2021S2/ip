public class Event extends Task {
    String date;

    public static Event createEvent(String input) {
        String[] details = input.split(" /at ");
        return new Event(details[0], details[1]);
    }

    private Event (String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date);
    }
}
