import java.util.ArrayList;

/**
 * When the user wishes to find a specific task,
 * Mister Duke will search through the current task list
 * for any matching tasks and display them to the user.
 */
public class FindCommand extends Command {
    private String command;

    public FindCommand(String command){
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String keyword = strArray[1];
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tempTaskList.add(task);
            }
        }
        return ui.showMatchingItems(tempTaskList);
    }

    public boolean isRunning() {
        return true;
    }
}
