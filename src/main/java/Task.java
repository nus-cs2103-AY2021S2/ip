public class Task {
    private String taskName;
    private boolean isTaskCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        isTaskCompleted = false;
    }

    public String setDone() {
        isTaskCompleted = true;
        return "Nice! I've marked this task as done: \n" +
                toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = isTaskCompleted ? "X" : " ";
        return "[" + taskStringCheck + "] " + taskName;
    }
}
