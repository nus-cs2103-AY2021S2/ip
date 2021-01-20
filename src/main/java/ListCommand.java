public class ListCommand implements Command {

    private TaskList currentList;

    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        currentList = taskList;
        return taskList;
    }

    @Override
    public String getResponse() {
        return currentList.toString();
    }
}
