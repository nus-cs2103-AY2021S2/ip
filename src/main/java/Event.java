public class Event extends Task {
    private String time;

    public Event(String title, String time) {
        super(title);
        this.time = time;
    }

    /**
     * @return a string describing the event task
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
