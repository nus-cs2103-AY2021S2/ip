public class Event extends Task{
    private String date;

    public Event(String input, String date) {
        super(input);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
