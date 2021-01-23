public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeCommandException {
        if(taskManager.getTasksSize() == 0){
            throw new DukeCommandException("delete", String.valueOf(index), "There are no task to be deleted.");
        } else if(index < 0 || index >= taskManager.getTasksSize()) {
            throw new DukeCommandException("delete", String.valueOf(index), "Please enter a valid task index ranging " +
                    "from 1 to " + taskManager.getTasksSize() + " (inclusive).");
        } else {
            try {
                Task task = taskManager.deleteTask(index);
                ui.printDeleteMsg(task, taskManager.getTasksSize());
            } catch(DukeTaskException e) {
                throw new DukeCommandException("delete", String.valueOf(index), e.getMessage());
            }
        }
    }
}
