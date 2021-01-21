public class ListCommand implements Command {

    @Override
    public String execute(final TaskList taskList) {
        if (taskList.size() != 0) {
            return DukeString.MESSAGE_LIST + taskList.toString();
        }
        return DukeString.MESSAGE_LIST_EMPTY;
    }
}
