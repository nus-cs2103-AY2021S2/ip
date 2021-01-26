public class DoneCommand extends Command {

    private int index;

    private static final String NO_INDEX_ERROR = "Please specify a task index!";
    private static final String INDEX_NOT_INT_ERROR = "Please specify a number instead!";
    private static final String OUT_OF_BOUND_ERROR = "Not in the list!";

    public DoneCommand(String index) throws DukeException {
        if (index.equals("")) {
            throw new DukeException(NO_INDEX_ERROR);
        }
        try {
            this.index = Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            throw new DukeException(INDEX_NOT_INT_ERROR);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            Task targetTask = tasks.getTask(index);
            targetTask.setDone(true);
            ui.printDoneMsg(targetTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OUT_OF_BOUND_ERROR);
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
