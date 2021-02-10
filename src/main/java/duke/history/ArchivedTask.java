package duke.history;

import duke.task.Task;

public class ArchivedTask {
    protected Integer index;
    protected Task task;

    public ArchivedTask(Integer index, Task task) {
        this.index = index;
        this.task = task;
    }

    public Integer getIndex() {
        return index;
    }

    public Task getTask() {
        return task;
    }
}
