public class Deadline extends Task {
    protected String by;

    public Deadline(String des, String by) {
        super(des);
        this.by = by;
    }

    public static Deadline makeDeadline(String line) throws DukeException {
        if (line.equals("")) {
            throw new DukeException("☹ OOPS!!!The description of a todo cannot be empty.\n");
        } else {
            String[] split = line.split("/by");
            String task = split[0];
            String by = split[1];
            return new Deadline(task, by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}