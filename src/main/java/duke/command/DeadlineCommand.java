package duke.command;

public class DeadlineCommand extends Command{
    String description;
    String deadline;

    public DeadlineCommand(String description, String deadline) {
        super("deadline");
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public void run() {

    }
}
