package duke.logic.commands;

import static duke.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.commons.util.AppUtil.checkContainsNumber;
import static duke.commons.util.CommandUtil.ALL;
import static duke.commons.util.CommandUtil.checkIndexOutOfBounds;
import static duke.commons.util.CommandUtil.checkListIsEmpty;
import static duke.commons.util.CommandUtil.parseStringToNumbers;

import java.util.List;

import duke.commons.exceptions.DukeException;
import duke.model.task.Task;
import duke.model.task.TaskList;
import duke.storage.Storage;
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
     * @throws DukeException when the user's input is in an invalid or incorrect format.
     */
    @Override
    public CommandResponse execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        checkListIsEmpty(taskList, false);
        String response;
        if (input.equals(ALL)) {
            response = processDeleteAllCommand(taskList, ui);
        } else if (checkContainsNumber(input)) {
            response = processDeleteCommand(taskList, ui);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        storage.saveFile(taskList);
        return new CommandResponse(response);
    }

    /**
     * Performs the deletion of all the {@code Task} in the {@code TaskList}.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @return {@code String} message to be outputted to user
     */
    private String processDeleteAllCommand(TaskList taskList, Ui ui) {
        TaskList printTaskList = taskList.clear();
        assert taskList.size() > 0 : "task list should be emptied";
        return ui.showDeleteMessage(printTaskList);
    }

    /**
     * Performs the deletion of multiple {@code Task} in the {@code TaskList}.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @return {@code String} message to be outputted to user
     */
    private String processDeleteCommand(TaskList taskList, Ui ui) throws DukeException {
        TaskList printTaskList = new TaskList();
        int listSize = taskList.size();
        List<Integer> indexes = parseStringToNumbers(input);
        for (int i: indexes) {
            int index = checkIndexOutOfBounds(i, listSize);
            printTaskList.add(deleteTask(taskList, index));
        }
        printTaskList.reverse();
        return ui.showDeleteMessage(printTaskList, taskList.size());
    }

    /**
     * Performs the deletion of a {@code Task} given an index.
     *
     * @param taskList user's task list
     * @param index index of task
     * @return Deleted {@code Task}
     */
    private Task deleteTask(TaskList taskList, int index) {
        Task task = taskList.delete(index);
        assert taskList.get(index) == null : "task should be deleted";
        return task;
    }
}
