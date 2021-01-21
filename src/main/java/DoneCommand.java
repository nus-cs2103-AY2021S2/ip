public class DoneCommand implements Command {
    public final int index;

    public DoneCommand(int idx) {
        this.index = idx;
    }

    @Override
    public String execute(TaskList taskList) {
        if (index > taskList.size()) {
            throw new DukeException.InvalidTask();
        }
        return String.format("%s\n%s", DukeString.MESSAGE_DONE, taskList.doneTask(index));
    }
}
