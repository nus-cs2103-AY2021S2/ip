public class Task {
    private static int count = 1;
    private int index;
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.index = count;
        count++;
        this.name = name;
        this.isCompleted = false;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isCompleted;
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String str = String.format("[%s] %s", isCompleted ? "X" : " ", name);
        return str;
    }
}