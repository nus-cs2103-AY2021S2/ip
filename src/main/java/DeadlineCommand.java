public class DeadlineCommand extends Command {
    private String event;
    private String deadline;

    DeadlineCommand(String event, String deadline) {
        this.event = event;
        this.deadline = deadline;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(this.event, this.deadline);
        tasks.add(task);
        ui.addtask(task.toString(), tasks.size());
        storage.savetasks(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof DeadlineCommand) {
            DeadlineCommand dlc = (DeadlineCommand) obj;
            return dlc.event.equals(this.event) && dlc.deadline.equals(this.deadline);
        }
        return false;
    }
}
