package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.MatchStringNotFoundException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Responsible for finding task.
 */
public class FindCommand extends Command {
    private String fullCommand;

    /**
     * Constructs a FindCommand with given full command line.
     *
     * @param fullCommand Full command line input.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns a string listing the tasks that matches given word in command line.
     *
     * @param tasks TasksList to find from.
     * @param ui Ui for system outputs.
     * @param storage Storage for saving contents into file.
     * @return String of tasks matching given word in command line.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchList = new TaskList(new ArrayList<Task>());
        String matchString = fullCommand.substring(4).trim();
        if (matchString.isEmpty()) {
            throw new MatchStringNotFoundException();
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.getTask(i);
            if (temp.getDescription().contains(matchString)) {
                matchList.add(temp);
            }
        }
        if (matchList.size() != 0) {
            return ui.getMatchList(matchList);
        } else {
            return ui.getNoMatchString();
        }
    }
}
