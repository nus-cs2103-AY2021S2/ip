public class Event extends Task {
    private final String date;

    public Event(String description, String date) throws InvalidFormatException {
        super(description, "E");
        this.date = date;

        if(date.isEmpty())
            throw new InvalidFormatException("Please specify both task description and date/time using /at");
    }

    @Override
    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + " (by: " + this.date + ")";
    }
}
