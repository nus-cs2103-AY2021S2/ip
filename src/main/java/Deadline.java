public class Deadline extends DatedTask {

    public Deadline(String task, String date) throws DukeException {
        super(task, date);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }
}
