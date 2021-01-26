public class TaskNotFoundException extends IndexOutOfBoundsException {
    public TaskNotFoundException() {
        super("☹ Sorry, I was not able to find the task :-(");
    }
}
