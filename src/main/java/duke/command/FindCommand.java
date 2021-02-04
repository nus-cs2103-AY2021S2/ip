package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

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
     * @throws DukeException if insufficient arguments are passed or arguments are invalid.
     */
    @Override
    public void process() throws DukeException {
        if (command.length != 2) {
            throw new DukeException("Please give me ONE keyword to look out for!");
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
            throw new DukeException("It seems my search button is malfunctioning...");
        }
    }
}
