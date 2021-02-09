package duke.commands;

import static duke.common.CommandUtils.ALL;
import static duke.common.CommandUtils.assertInputs;
import static duke.common.CommandUtils.checkIndexOutOfBounds;
import static duke.common.CommandUtils.checkListIsEmpty;

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
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assertInputs(input);
        int listSize = checkListIsEmpty(taskList, false);

        String returnMessage;
        if (input.equals(ALL)) {
            TaskList printTaskList = taskList.clear();
            assert taskList.size() > 0 : "taskList should be emptied";
            returnMessage = ui.showDeleteMessage(printTaskList);
        } else {
            int index = Integer.parseInt(input) - 1;
            checkIndexOutOfBounds(index, listSize);
            assert index >= 0 : "input should not be negative";
            Task task = taskList.delete(index);
            assert taskList.get(index) == null : "task should be deleted";
            returnMessage = ui.showDeleteMessage(task, taskList.size());
        }
        storage.saveFile(taskList);
        return new CommandResponse(returnMessage);
    }
}
