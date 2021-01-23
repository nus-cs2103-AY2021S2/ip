public class Event extends Task {
    private final String date;

    public Event(String description, String date) throws InvalidFormatException {
        super(description, "E");
        this.date = date;

        if(date.isEmpty())
            throw new InvalidFormatException("Please specify both task description and date/time using /at");
    }

    public String fileFormat() {
        return "E | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + " (by: " + this.date + ")";
    }
}
