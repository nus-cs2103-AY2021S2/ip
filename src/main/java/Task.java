public class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public void finished() {
        done = true;
    }

    protected String statusicon() {
        if (done) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }


    @Override
    public String toString() {
        return this.statusicon() + description;
    }
}
