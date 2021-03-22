package task;

public class TodoTask extends Task {

    /**
     * Constructs new task with provided taskDescription and deadline
     * @param taskDescription description of task
     */
    public TodoTask(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskType = 'T';
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[%c] [X] %s", this.taskType, this.taskDescription);
        } else {
            return String.format("[%c] [ ] %s", this.taskType, this.taskDescription);
        }
    }
}
