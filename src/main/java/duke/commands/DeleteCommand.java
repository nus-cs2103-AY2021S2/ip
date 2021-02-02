package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Deletes task from the list based on given index, or delete everything from the list.
 */
public class DeleteCommand extends Command {

    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Performs deletion of tasks, prints a message to the user and update the storage file.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @param storage storage object
     * @throws DukeException if there were errors encountered when saving the file
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int listSize = taskList.size();
        if (listSize <= 0) {
            throw new DukeException("Your task list is empty.");
        }

        String returnMessage;
        if (input.equals("all")) {
            TaskList tl = taskList.clone();
            taskList.clear();
            storage.saveFile(taskList);
            returnMessage = ui.showDeleteMessage(tl);
        } else {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= listSize) {
                throw new DukeException("The number you have entered is out of bound.");
            }

            Task task = taskList.delete(index);
            storage.saveFile(taskList);
            returnMessage = ui.showDeleteMessage(task, taskList.size());
        }
        storage.saveFile(taskList);
        return returnMessage;
    }
}
