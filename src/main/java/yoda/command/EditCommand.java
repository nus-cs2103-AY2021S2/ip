package yoda.command;

import java.util.ArrayList;
import java.util.List;

import yoda.storage.Storage;
import yoda.task.InvalidTaskListIndexException;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * EditCommand class that handles task states and a child of the Command class.
 */
public class EditCommand extends Command {
    /**
     * Creates an EditCommand object.
     * @param details Details of EditCommand object.
     */
    public EditCommand(String[] details) {
        super(details);
        commandType = CommandType.valueOf(details[0]);
    }

    /**
     * Accesses the tasks from the TaskList that are about to be edited and puts them in another TaskList.
     * @param taskList Tasklist from which the tasks are being accessed.
     * @return TaskList containing the tasks which were accessed to be edited.
     * @throws InvalidTaskListIndexException If the user does not provide index or provides an invalid task index.
     */
    public TaskList accessTasksToBeEdited(TaskList taskList) throws InvalidTaskListIndexException {
        if (details.length == 1) {
            throw new InvalidTaskListIndexException("Enter the list number of task to be edited, you must!");
        }
        if (details[1].equals("all")) {
            return new TaskList(taskList.getList());
        }
        try {
            List<Task> accessedTasks = new ArrayList<>();
            for (int i = 1; i < details.length; i++) {
                int taskToBeEdited = Integer.parseInt(details[i]);
                Task task = taskList.accessTask(taskToBeEdited);
                accessedTasks.add(task);
            }
            return new TaskList(accessedTasks);
        } catch (NumberFormatException e) {
            throw new InvalidTaskListIndexException("Strong within you, the dark force is.\n"
                                                    + "Integer, you must enter. Nothing else!");
        }
    }

    /**
     * Deletes the tasks specified by user from the TaskList.
     * @param taskList TaskList from which the tasks are deleted
     */
    public void deleteTasks(TaskList taskList) {
        if (details[1].equals("all")) {
            taskList.deleteAllTasks();
        } else {
            for (int i = 1; i < details.length; i++) {
                taskList.markTaskToBeDeleted(Integer.parseInt(details[i]));
            }
            taskList.deleteMarkedTasks();
        }
    }

    /**
     * Marks the tasks specified by user as done in the TaskList.
     * @param taskList TaskList in which the tasks are marked as done.
     */
    public void markTasksAsDone(TaskList taskList) {
        if (details[1].equals("all")) {
            taskList.markAllTasksAsDone();
        } else {
            for (int i = 1; i < details.length; i++) {
                taskList.markTaskAsDone(Integer.parseInt(details[i]));
            }
        }
    }

    /**
     * Deletes or marks as done the tasks specified by user in their issued command and
     * writes updated Tasklist to the file.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     * @return Message to user informing if the EditCommand was executed successfully or not.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            TaskList accessedTasks = accessTasksToBeEdited(taskList);
            assert accessedTasks.getTaskListSize() != 0 : "There must be at least one task accessed";
            if (commandType == CommandType.DELETE) {
                deleteTasks(taskList);
                storage.serialize(taskList);
                return ui.printDeletedTask(accessedTasks.toString(), taskList.getTaskListSize());
            } else if (commandType == CommandType.DONE) {
                markTasksAsDone(taskList);
                storage.serialize(taskList);
                return ui.printFinishedTask(accessedTasks.toString(), taskList.getNumberOfUnfinishedTasks());
            } else {
                return "The greatest teacher, failure is.";
            }
        } catch (InvalidTaskListIndexException e) {
            return e.getMessage();
        }
    }
}
