package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class UndoCommand implements Command {
    /**
     * Displays the current state of taskList after undo action.
     *
     * @param taskList
     * @param storage
     * @return String
     * @throws DukeException
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        StringBuilder result = new StringBuilder();
        result.append("Your previous command is undone.\n");
        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, taskList.get(i).toString()));
        }
        return result.toString();
    }
}
