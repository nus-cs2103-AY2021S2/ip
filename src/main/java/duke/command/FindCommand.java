package duke.command;

import java.util.ArrayList;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task t : taskList.getTasks()) {
            if (t.getName().contains(this.keyword)) {
                tasks.add(t);
            }
        }
        return ui.showFound(tasks);
    }
}
