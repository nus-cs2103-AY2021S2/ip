import java.util.ArrayList;

/**
 * The type Find command.
 */
public class FindCommand extends Command {
    private final String fullCommand;

    /**
     * Instantiates a new Find command.
     *
     * @param fullCommand the full command
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException.EmptyTaskListError, DukeException.NoMatchFound {
                //find the keyword in taskList
                if (taskList.getTasksCount() == 0) {
                    throw new DukeException.EmptyTaskListError("No Tasks were added");
                } else {
                    String keyWord = fullCommand.substring(5);
                    ArrayList<Task> foundTasks = new ArrayList<>();
                    for (int i = 0; i < taskList.getTasksCount(); i++) {
                        if (taskList.getTasksList().get(i).description.contains(keyWord)) {
                            foundTasks.add(taskList.getTasksList().get(i));
                        }
                    }
                    if (!foundTasks.isEmpty()) {
                        TaskList foundTaskList = new TaskList(foundTasks);
                        ui.foundMsg(foundTaskList);
                    } else {
                        throw new DukeException.NoMatchFound("No Task Found containing '"
                                + keyWord + "'");
                    }
                }
    }
    public Boolean isExit() {
        return false;
    }
}
