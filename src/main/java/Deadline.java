public class Deadline extends Task {
    private final String deadline;

    public Deadline(String[] input) {
        super(input[0]);
        deadline = input[1].substring(3);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
