public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        if (taskList.size() != 0) {
            return DukeStrings.MESSAGE_LIST + taskList.toString();
        }
        return DukeStrings.MESSAGE_LIST_EMPTY;
    }
}
