public class Task {
    protected String name;
    protected boolean completed;
    protected char cat;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }
    public char getCat() {
        return this.cat;
    }
    public String getName() {
        return this.name;
    }
    public boolean getCompleted() {
        return this.completed;
    }
    public void checkTask() {
        this.completed = true;
    }

    public String getStatusIcon() {
        return (completed ? "X" : " "); //return tick or blank
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name ;
    }
}
