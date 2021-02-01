package duke.exceptions;

/**
 * Represents the exceptions when marking a finished task done.
 */
public class TaskDoneException extends ChatBotException {
    public TaskDoneException() {
        super("RELAX!!! The task is already done!");
    }
}
