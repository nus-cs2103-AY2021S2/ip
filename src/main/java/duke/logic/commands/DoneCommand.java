package duke.logic.commands;

import static duke.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static duke.commons.core.Messages.MESSAGE_TASK_ALL_COMPLETED;
import static duke.commons.core.Messages.MESSAGE_TASK_COMPLETED;
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
 * Marks a task as done from the list based on given index, or mark everything as done from the list.
 */
public class DoneCommand extends Command {

    private final String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Performs marking of tasks as done, prints a message to the user and update the storage file.
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
            response = processDoneAllCommand(taskList, ui);
        } else if (checkContainsNumber(input)) {
            response = processDoneCommand(taskList, ui);
        } else {
            throw new DukeException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        storage.saveFile(taskList);
        return new CommandResponse(response);
    }

    /**
     * Performs the marking of all {@code Task} in the {@code TaskList} as done.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @return {@code String} message to be outputted to user
     * @throws DukeException when all the {@code Task} in the {@code TaskList} are already completed.
     */
    private String processDoneAllCommand(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.isAllDone()) {
            throw new DukeException(MESSAGE_TASK_ALL_COMPLETED);
        }
        taskList.setAllDone();
        assert taskList.isAllDone() : "all tasks should be marked as done";
        return ui.showDoneMessage(taskList);
    }

    /**
     * Performs the marking of multiple {@code Task} in the {@code TaskList} as done.
     *
     * @param taskList user's task list
     * @param ui text UI object
     * @return {@code String} message to be outputted to user
     */
    private String processDoneCommand(TaskList taskList, Ui ui) throws DukeException {
        TaskList printTaskList = new TaskList();
        int listSize = taskList.size();
        List<Integer> indexes = parseStringToNumbers(input);
        for (int i: indexes) {
            int index = checkIndexOutOfBounds(i, listSize);
            printTaskList.add(markTaskAsDone(taskList, index));
        }
        printTaskList.reverse();
        return ui.showDoneMessage(printTaskList);
    }

    /**
     * Performs the marking of a {@code Task} as done, given an index.
     *
     * @param taskList user's task list
     * @param index index of task
     * @return Completed {@code Task}
     */
    private Task markTaskAsDone(TaskList taskList, int index) throws DukeException {
        Task task = taskList.get(index);
        if (task.getDone()) {
            throw new DukeException(MESSAGE_TASK_COMPLETED);
        }
        task.setDone();
        assert task.getDone() : "task should be marked as done";
        return task;
    }
}
