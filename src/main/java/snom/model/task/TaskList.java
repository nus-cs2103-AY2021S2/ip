package snom.model.task;

import java.util.ArrayList;

import snom.common.core.Messages;
import snom.common.exceptions.SnomException;

public class TaskList extends ArrayList<Task> {

    /**
     * Returns a new task list with task description containing the given keyword
     *
     * @param keyword keyword to be searched
     * @return        new task list
     */
    public TaskList findTask(String keyword) {
        TaskList newList = new TaskList();
        for (Task task: this) {
            if (task.getDescription().contains(keyword)) {
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Set the task status by the given task numbers as finished.
     * Then prints out the complete messages.
     *
     * @param taskNums       task number list that needs to mark as finish
     * @return               a list of finished snom.tasks
     * @throws SnomException If the task number is not available in the task list.
     */
    public Task[] finishTask(int[] taskNums) throws SnomException {
        Task[] finishedTasks = new Task[taskNums.length];
        for (int i = 0; i < taskNums.length; i++) {
            int taskNo = taskNums[i] - 1;
            try {
                Task task = this.get(taskNo);
                task.setStatus(true);
                assert task.hasFinished() == true : "Task status should be set to true";
                finishedTasks[i] = task;
            } catch (IndexOutOfBoundsException e) {
                throw new SnomException(String.format(Messages.ERROR_INVALID_TASK_NUM, taskNums[i]));
            }
        }
        return finishedTasks;
    }

    /**
     * Removes the given task numbers from the task list.
     * Then prints out the deleted messages.
     *
     * @param  taskNums      task number list that needs to be removed
     * @return               a list of deleted snom.tasks
     * @throws SnomException If the task number is not available in the task list.
     */
    public Task[] deleteTask(int[] taskNums) throws SnomException {
        Task[] deletedTasks = new Task[taskNums.length];
        for (int i = 0; i < taskNums.length; i++) {
            int taskNo = taskNums[i] - 1 - i;
            try {
                Task task = this.get(taskNo);
                this.remove(task);
                deletedTasks[i] = task;
            } catch (IndexOutOfBoundsException e) {
                throw new SnomException(String.format(Messages.ERROR_INVALID_TASK_NUM, taskNums[i]));
            }
        }
        return deletedTasks;
    }
}
