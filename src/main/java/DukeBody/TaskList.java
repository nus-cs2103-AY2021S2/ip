package dukebody;

import java.util.ArrayList;

import duketask.Task;

/**
 * ArrayList derived class representing a list of tasks with
 * added functionality to mark tasks as done at indexes.
 */
public class TaskList extends ArrayList<Task> {
    private static final long serialVersionUID = 6951591508963981354L;

    // accessors
    public TaskList getUndoneTasks() {
        TaskList undone = new TaskList();

        for (Task task: this) {
            if (!task.isDone()) {
                undone.add(task);
            }
        }

        return undone;
    }

    public TaskList getMatchedTasks(String keyphrase) {
        TaskList matched = new TaskList();

        for (Task task: this) {
            if (task.inDescription(keyphrase)) {
                matched.add(task);
            }
        }

        return matched;
    }


    // mutators
    public void markAsDone (int taskIndex) throws IndexOutOfBoundsException,
            Task.MarkedAsDoneException {
        this.get(taskIndex).markAsDone();
    }
}
