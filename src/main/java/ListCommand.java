public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.EmptyTaskListError {
                if (taskList.getTasksCount() == 0) {
                    throw new DukeException.EmptyTaskListError("No Tasks were added");
                } else {
                    //System.out.println(taskList.toString());
                    ui.listMsg(taskList);
                }
    }
    public Boolean isExit() {
        return false;
    }
}
