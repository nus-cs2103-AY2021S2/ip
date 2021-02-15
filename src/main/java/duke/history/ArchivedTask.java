package duke.history;

import duke.task.Task;

/**
 * Archived task consists of the task that was delete from the task list and its index.
 *
 * @author  Nicole Ang
 */
public class ArchivedTask {
    protected Integer index;
    protected Task task;

    /**
     * Constructs a new ArchivedTask object given the task and its index in the task list.
     *
     * @param index Index in task list.
     * @param task Archived task.
     * @return Feedback message.
     */
    public ArchivedTask(Integer index, Task task) {
        this.index = index;
        this.task = task;
    }

    /**
     * Returns index of task.
     *
     * @return Task index.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Returns task.
     *
     * @return Task.
     */
    public Task getTask() {
        return task;
    }
}
