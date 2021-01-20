public class Todo extends Task {

    public Todo(String taskDescription) {
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
