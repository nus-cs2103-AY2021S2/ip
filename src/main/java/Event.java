public class Event extends Task{
    private static final char SYMBOL = 'E';
    private String deadline;

    public Event(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.deadline);
    }

    @Override
    public String save() {
        return String.format("%c,%s,%s\n", SYMBOL, super.save(), this.deadline);
    }
}
