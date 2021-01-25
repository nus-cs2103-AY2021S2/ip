public class Deadline extends Task {

    private final String date;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
    }

    public Deadline(String description, String date, Boolean completed) {
        super(description, TaskType.DEADLINE, completed);
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

    public String storageEntry() {
        StringBuilder output = new StringBuilder(super.storageEntry());
        output.append("|");
        output.append(this.date);
        return output.toString();
    }

    public static Deadline parseDeadline(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!description.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain a time.");
        }
        String[] partitioned = description.split("/by");
        return new Deadline(partitioned[0].strip(), partitioned[1].strip());
    }
}
