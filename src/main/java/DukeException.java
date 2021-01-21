public class DukeException extends Exception {
    /***
     * Constructor for DukeException to handle incorrect inputs not caught by compiler
     * @param taskType String that indicates the task
     */
    public DukeException(String taskType) {
        super(taskType + " cannot be added without a description. Try again!");
    }
}
