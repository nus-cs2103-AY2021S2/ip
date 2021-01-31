package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String fullCommand, String keyword) {
        super(fullCommand);
        this.keyword = keyword;
    }

    /**
     * Executes the user command to exit duke.Duke.
     * @param tasks A TaskList object which encapsulates the data and operations on a task list.
     * @param ui A duke.Ui object which deals with interactions with the user.
     * @param storage A duke.Storage object which deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.find(keyword);
        for (int i = 0; i < result.size(); i++) {
            ui.printMsg((i + 1) + "." + result.get(i).toString());
        }
    }

    public boolean isExit() {
        return false;
    }
}
