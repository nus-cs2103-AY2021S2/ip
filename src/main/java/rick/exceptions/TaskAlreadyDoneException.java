package rick.exceptions;

public class TaskAlreadyDoneException  extends RickException {
    @Override
    public String getMessage() {
        return "Task already done!";
    }
}