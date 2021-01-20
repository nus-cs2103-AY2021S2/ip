public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return taskList.toString();
    }
}
