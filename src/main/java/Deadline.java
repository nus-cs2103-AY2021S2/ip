public class Deadline extends Task {
    private final String date;

    public Deadline(String description, String date) throws InvalidFormatException {
        super(description, "D");
        this.date = date;

        if(date.isEmpty())
            throw new InvalidFormatException("Please specify both task description and date/time using /by");
    }

    @Override
    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + " (by: " + this.date + ")";
    }
}
