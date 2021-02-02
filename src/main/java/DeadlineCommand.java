public class DeadlineCommand extends Command {
    String by;

    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        taskList.addDeadlineTask(deadline);
        storage.writeToFile(taskList.getList());
        ui.showTaskAdded(deadline);
    }
}
