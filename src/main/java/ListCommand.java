public class ListCommand extends Command {
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.list();
    }

    public boolean isExit() {
        return false;
    }
}
