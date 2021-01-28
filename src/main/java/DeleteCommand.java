public class DeleteCommand extends Command {

    public DeleteCommand(String command, String task, String date) {
        super(command, task, date);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
            int index = Integer.valueOf(this.task);
            Task curTask = taskList.get(index);
            taskList.delete(index);
            ui.addDeleteString(taskList.size(), curTask.toString());
            storage.rewrite(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
    
}
