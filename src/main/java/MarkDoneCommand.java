public class MarkDoneCommand implements Command{

    private final int taskIndex;

    public MarkDoneCommand(int i) {
        this.taskIndex = i;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        try {
            tasks.markDone(this.taskIndex);
            ui.showMarkDone(this.taskIndex, tasks.get(this.taskIndex));
        } catch (IndexOutOfBoundsException e) {
            ui.showIndexException(tasks.size(), "tasks");
        }
        return true;
    }
}
