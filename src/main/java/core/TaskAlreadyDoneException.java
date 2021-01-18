package core;

public class TaskAlreadyDoneException extends DukeException{
    TaskAlreadyDoneException() {
        super("Task has already been done!");
    }

}
