package duke;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a due command keyed in by the user.
 */
public class DueCommand extends Command {

    DueCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes the due command by printing tasks that specifically end on a specified date.
     *
     * @param taskManager TaskManager object that maintains the list of tasks.
     * @param ui Ui object that handles user interaction.
     * @param storage Storage object that handles the updating of stored file.
     */
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        String date = parsedCommand[1];
        ArrayList<Task> list = taskManager.getTasksOn(date);
        ui.showDueTasks(list, date);
    }
}
