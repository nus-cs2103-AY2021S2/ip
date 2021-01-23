public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            taskManager.deleteTask(index);
        } catch(DukeTaskException e) {
            throw new DukeCommandException("delete", String.valueOf(index), e.getMessage());
        }
    }
}
