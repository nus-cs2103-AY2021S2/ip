package yoda.command;

import yoda.storage.Storage;
import yoda.task.InvalidTaskListIndexException;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.ui.Ui;

import java.util.ArrayList;
import java.util.List;

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

    public TaskList accessTasksToBeEdited(TaskList taskList) throws InvalidTaskListIndexException {
        if (details.length == 1) {
            throw new InvalidTaskListIndexException("Enter the number of task to be edited, you must!");
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

    public void deleteTasks(TaskList taskList) {
        for (int i = 1; i < details.length; i++) {
            taskList.markTaskToBeDeleted(Integer.parseInt(details[i]));
        }
        taskList.deleteMarkedTasks();
    }

    public void markTasksAsDone(TaskList taskList) {
        for (int i = 1; i < details.length; i++) {
            taskList.markTaskAsDone(Integer.parseInt(details[i]));
        }
    }
    /**
     * Deletes a task or marks a task as done based on the command issued by user.
     * @param taskList TaskList associated with the EditCommand being executed.
     * @param ui Ui associated with the EditCommand being executed.
     * @param storage Storage associated with the EditCommand being executed.
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
