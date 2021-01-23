public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            taskManager.completeTask(index);
        } catch(DukeTaskException e) {
            throw new DukeCommandException("done", String.valueOf(index), e.getMessage());
        }
    }
}
