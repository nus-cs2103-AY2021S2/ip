package duke.exceptions;

public class TaskDoneException extends ChatBotException {
    public TaskDoneException() {
        super("RELAX!!! The task is already done!");
    }
}
