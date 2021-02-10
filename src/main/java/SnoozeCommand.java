import java.io.IOException;

public class SnoozeCommand extends Command {
    private final int taskNum;

    /**
     * Constructor to keep track of the task number being marked as done.
     * @param taskNum The task number that is to be marked as done.
     */
    public SnoozeCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task as done and updates the file in hard drive accordingly.
     * @param taskList TaskList that contains all tasks at hand.
     * @param ui Ui that deals with interaction with the user.
     * @param storage Storage that deals with storing TaskList into hard drive.
     * @throws IOException If there is an error while updating the file in hard drive.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        String toReturn = ui.printSnoozedTask(taskList.getNormalTasks().get(this.taskNum - 1));
        taskList.snoozeTask(this.taskNum - 1);
        storage.update(taskList);
        return toReturn;
    }
}
