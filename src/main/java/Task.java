public class Task {
    protected String description;
    protected boolean done;
    protected TaskEnum type;

    public Task() {

    }

    public Task(String input) {
        this.description = input;
        done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean set) {
        this.done = set;
    }

}
