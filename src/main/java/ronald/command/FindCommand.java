package ronald.command;

import java.util.ArrayList;

import ronald.RonaldException;
import ronald.ui.Ui;
import ronald.storage.Storage;
import ronald.task.Task;

/**
 * Class containing data and methods specific to a Find command.
 */
public class FindCommand extends Command {

    public FindCommand(String[] command) {
        super.command = command;
    }

    /**
     * Finds all tasks matching the keyword obtained from the arguments from initialisation.
     *
     * @throws RonaldException if insufficient arguments are passed or arguments are invalid.
     */
    @Override
    public void process() throws RonaldException {
        if (command.length != 2) {
            throw new RonaldException("Please give me ONE keyword to look out for!");
        }
        try {
            String keyword = command[1];
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : Storage.getTasks()) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            Ui.displayMatchingTasks(matchingTasks);
        } catch (Exception e) {
            throw new RonaldException("It seems my search button is malfunctioning...");
        }
    }
}
