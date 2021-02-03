package Command;
import Oracle.TaskList;
import Oracle.Ui;
import Entry.Deadline;

public class DeadlineCommand implements Command {
    private final String taskDescription;
    private final String taskDeadline;

    public DeadlineCommand(String taskDescription, String taskDeadline) {
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            tasks.add(new Deadline(this.taskDescription, this.taskDeadline));
        } catch (CommandFormatException e) {
            ui.showFormatException("Command.EventCommand");
            return true;
        }
        ui.showNewTask(tasks.size(), tasks.get(tasks.size() - 1));
        return true;
    }
}
