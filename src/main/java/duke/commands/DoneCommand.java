package duke.commands;

import static duke.common.CommandUtils.ALL;
import static duke.common.CommandUtils.assertInputs;
import static duke.common.CommandUtils.checkIndexOutOfBounds;
import static duke.common.CommandUtils.checkListIsEmpty;
import static duke.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.common.Messages.MESSAGE_TASK_ALL_COMPLETED;
import static duke.common.Messages.MESSAGE_TASK_COMPLETED;
import static duke.common.Utils.checkIsNumeric;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as done from the list based on given index, or mark everything as done from the list.
 */
public class DoneCommand extends Command {

    private final String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Performs completion of tasks, prints a message to the user and update the storage file.
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

        String response;
        if (input.equals(ALL)) {
            response = processDoneAllCommand(taskList, ui);
        } else if (checkIsNumeric(input)) {
            response = processDoneCommand(taskList, ui, input, listSize);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        storage.saveFile(taskList);
        return new CommandResponse(response);
    }

    private String processDoneAllCommand(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.isAllDone()) {
            throw new DukeException(MESSAGE_TASK_ALL_COMPLETED);
        }
        taskList.setAllDone();
        return ui.showDoneMessage(taskList);
    }

    private String processDoneCommand(TaskList taskList, Ui ui, String input, int listSize) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        checkIndexOutOfBounds(index, listSize);
        assert index >= 0 : "input should not be negative";
        Task task = taskList.get(index);
        if (task.getDone()) {
            throw new DukeException(MESSAGE_TASK_COMPLETED);
        }
        task.setDone();
        assert task.getDone() : "task should be set as done";
        return ui.showDoneMessage(task);
    }
}
