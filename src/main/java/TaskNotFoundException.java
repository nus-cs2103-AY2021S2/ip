public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("Task not in list.");
    }
}
