package duke.error;

public class IllegalTaskException extends StringIndexOutOfBoundsException {
    protected String taskType;

    public IllegalTaskException(String message) {
        super(message);
    }

    public IllegalTaskException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "You forgot to enter the description of the " + taskType + "!\n"
                + (char) 9 + (char) 9 + "Please re-enter!";
    }
}
