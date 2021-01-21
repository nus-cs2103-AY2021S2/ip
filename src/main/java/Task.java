public class Task {

    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return this.description;
    }


}
