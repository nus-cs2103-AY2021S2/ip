public class Task {
    private static String[] tasks = new String[100];
    private String task;
    private static int capacity = 0;
    Task(String task) {
        this.task = task;
        capacity++;
        add(task);
    }

    private static void add(String s) {
        tasks[capacity - 1] = s;
    }

    public static String[] getTasks() {
        return tasks;
    }
}
