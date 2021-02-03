package duke;

/**
 * Class Task represents a task that can be taken note by Danh's Duke.
 * Task has 3 types: ToDo, Deadline, Event.
 */
class Task {
    private String taskName;
    private boolean taskDone;

    /**
     * return a Task with specified taskName, this task is not done yet.
     *
     * @param taskName The name of the Task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    /**
     * Returns a String, which is the expression of a Task.
     *
     * @return Task expression.
     */
    public String printTask() {
        String ans;
        if (taskDone) {
            ans = "[X] " + this.taskName;
        } else {
            ans = "[ ] " + this.taskName;
        }
        return ans;
    }

    /**
     * Mark a task as (Done).
     */
    public void markAsDone() {
        this.taskDone = true;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public String getTaskName() {
        return taskName;
    }
}
