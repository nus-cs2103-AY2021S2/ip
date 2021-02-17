public class SortCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
        throws DukeException.EmptyTaskListError {
            if (taskList.getTasksCount() == 0) {
                throw new DukeException.EmptyTaskListError("No Tasks were added");
            } else {
                TaskList sortedTL = taskList.dateSort();
                ui.listMsg(sortedTL);
            }

    }

    @Override
    public Boolean isExit() {
        return null;
    }
}
