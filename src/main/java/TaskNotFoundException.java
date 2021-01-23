//public class TaskNotFoundException extends Exception {
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("\tTask not in list.\n");
    }
}
