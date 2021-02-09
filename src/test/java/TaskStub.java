import duke.Task;

public class TaskStub extends Task {

    TaskStub(String description) {
        super(description);
    }
    public void setDone(boolean isDone) {
        super.isDone = isDone;
    }
    public boolean getDone() {
        return super.isDone;
    }
}
