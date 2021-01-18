public class Task {
    private boolean isDone;
    private String name;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
