public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeCommandException {
        if(taskManager.getTasksSize() == 0) {
            throw new DukeCommandException("done", String.valueOf(this.index), "There are no task to be completed.");
        } else if(index < 0 || index >= taskManager.getTasksSize()) {
            throw new DukeCommandException("done", String.valueOf(this.index), "Please enter a valid task index ranging " +
                    "from 1 to " + taskManager.getTasksSize() + " (inclusive).");
        } else {
            try {
                taskManager.completeTask(this.index);
                ui.printDoneMsg(this.index, taskManager.getTask(this.index));
            } catch(DukeTaskException e) {
                throw new DukeCommandException("done", String.valueOf(index), e.getMessage());
            }
        }
    }
}
