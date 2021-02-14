import java.util.ArrayList;

/**
 * When the user marks a specified task as done,
 * the DoneCommand is called
 */
public class DoneCommand extends Command {
    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        if (Integer.parseInt(commandArray[1]) > taskList.size() || Integer.parseInt(commandArray[1]) == 0) {
            return ui.showOutOfBounds();
        } else {
            Task completedTask = taskList.get(Integer.parseInt(commandArray[1]) - 1);
            completedTask.markAsDone();
            return ui.showTaskDone(completedTask);
        }
    }

    public boolean isRunning() {
        return true;
    }

}
