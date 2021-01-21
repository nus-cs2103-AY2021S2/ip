public class Deadline extends Task{
    String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.name + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.name + " (by: " + this.deadline + ")";
        }
    }
}
