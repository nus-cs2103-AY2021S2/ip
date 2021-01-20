public class ToDo extends Task {

    public ToDo(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.taskDescription;
        } else {
            return "[ ] " + this.taskDescription;
        }
    }
}
