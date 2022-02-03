package duke;

/**
 * Class Task represents a task that can be taken note by Danh's Duke.
 * Task has 3 types: ToDo, Deadline, Event.
 */
class Task {
    private final String taskName;
    private boolean isTaskDone;

    /**
     * Returns a Task with specified taskName, this task is not done yet.
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isTaskDone = false;
    }

    /**
     * Returns a String, which is the expression of a Task.
     *
     * @return Task expression.
     */
    public String printTask() {
        String ans;
        if (isTaskDone) {
            ans = "[X] " + this.taskName;
        } else {
            ans = "[ ] " + this.taskName;
        }
        return ans;
    }

    /**
     * Marks a task as (Done).
     */
    public void markAsDone() {
        this.isTaskDone = true;
    }

    /**
     * Returns the information whether a task is done or not.
     *
     * @return a boolean containing this information.
     */
    public boolean isTaskDone() {
        return isTaskDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return taskName;
    }
}
