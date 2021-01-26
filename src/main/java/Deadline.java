public class Deadline extends Task{
    private static final char SYMBOL = 'D';
    private String deadline;

    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s (by: %s)", SYMBOL, super.toString(), this.deadline);
    }

    @Override
    public String save() {
        return String.format("%c,%s,%s\n", SYMBOL, super.save(), this.deadline);
    }
}
