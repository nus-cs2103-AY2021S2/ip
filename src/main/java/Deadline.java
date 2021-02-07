public class Deadline extends Command {

    public String dueDate;

    public Deadline(String commandDescription, String dueDate) {
        super(commandDescription);
        this.isDone = false;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " | by: " + dueDate;
    }
}
