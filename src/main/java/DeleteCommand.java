/**
   * DeleteCommand inherits Command
   * @param command the main action of the command
   * @param task the index of task user wants to delete
   * @param date is a null value for DeLete Command
   */

public class DeleteCommand extends Command {

    public DeleteCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            int index = Integer.valueOf(this.task);
            Task curTask = taskList.get(index);
            taskList.delete(index);

            storage.rewrite(taskList);
        return ui.addDeleteString(taskList.size(), curTask.toString());
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
