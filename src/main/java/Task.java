public class Task {
    private static Task[] taskList = new Task[100];
    private String taskName;
    private static int capacity = 0;
    private int index;
    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        capacity++;
        add(this);
    }

    private static void add(Task t) {
        taskList[capacity - 1] = t;
    }

    public String getTaskName() {
        return taskName;
    }

    public Task[] getTaskList() {
        return Task.taskList;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", index, taskName);
    }
}
