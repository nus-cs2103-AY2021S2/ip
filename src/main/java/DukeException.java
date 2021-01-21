public class DukeException extends Exception {

    public DukeException(String taskType) {
        super(taskType + " cannot be added without a description. Try again!");
    }
}
