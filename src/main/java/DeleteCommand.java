import java.io.IOException;
import java.util.ArrayList;

/**
 * When the user deletes a specified task from the task list,
 * the DeleteCommand is called
 */
public class DeleteCommand extends Command {
    private String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        if (Integer.parseInt(commandArray[1]) > taskList.size() || Integer.parseInt(commandArray[1]) == 0) {
            return ui.showOutOfBounds();
        } else {
            try {
                storage.save(taskList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ui.showTaskDelete(taskList, commandArray[1]);
        }
    }

    public boolean isRunning() {
        return true;
    }

}
