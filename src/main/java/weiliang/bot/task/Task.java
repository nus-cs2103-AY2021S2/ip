package weiliang.bot.task;

public class Task {
    
    protected String task;
    protected boolean completed;

    public Task(String task) {
        this.task = task;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[T][" + (completed ? "X" : " ") + "] " + task;
    }
    
}
