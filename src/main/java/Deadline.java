public class Deadline extends Task {

    private final String date;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (by: ");
        output.append(this.date);
        output.append(")");
        return output.toString();
    }

    public static Deadline parseDeadline(String description) {
        String[] partitioned = description.split("/by");
        return new Deadline(partitioned[0].strip(), partitioned[1].strip());
    }
}
