public class Task {
    protected String description;
    protected boolean done;
    protected TaskEnum type;
    protected String date;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public TaskEnum getType() {
        return type;
    }

    public void setType(TaskEnum type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
