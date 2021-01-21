public class DoneCommand implements Command {
    public final int index;

    public DoneCommand(int idx) {
        this.index = idx;
    }

    @Override
    public String execute(TaskList taskList) {
        return String.format("%s\n%s", DukeString.MESSAGE_DONE, taskList.doneTask(index));
    }
}
