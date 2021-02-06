package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String kw;

    /**
     * Creates a FindCommand that will find tasks with matching keyword kw
     *
     * @param kw
     */
    public FindCommand(String kw) {
        super(false);
        this.kw = kw;
    }

    /**
     * Finds tasks with matching keyword kw
     *
     * @param taskList
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        int count = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).contains(kw)) {
                count++;
                result.append(String.format("%d.%s", count, taskList.get(i).toString()));
            }
        }
        return result.toString();
    }
}
