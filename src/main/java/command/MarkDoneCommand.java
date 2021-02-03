package command;
import oracle.TaskList;
import oracle.Ui;

public class MarkDoneCommand implements Command{

    private final int taskIndex;

    /**
     * @param i index of the task to be marked done
     */
    public MarkDoneCommand(int i) {
        this.taskIndex = i;
    }

    /**
     * @param ui let the user know that we marked the task done already
     * @param tasks we mark the indicated task in this TaskList as done
     * @return true
     */
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
