public class DoneCommand implements Command {
    private final int index;

    public DoneCommand(final int idx) {
        this.index = idx;
    }

    @Override
    public String execute(final TaskList taskList) {
        if (index > taskList.size()) {
            throw new DukeException.InvalidTask();
        }
        return String.format("%s\n%s", DukeString.MESSAGE_DONE, taskList.doneTask(index));
    }
}
