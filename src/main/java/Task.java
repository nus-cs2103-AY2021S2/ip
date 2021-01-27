public class Task {
    String task;
    Boolean completed;

    Task(String t) {
        this.task = t;
        this.completed = false;
    }

    Task(String t, Boolean completed) {
        this.task = t;
        this.completed = completed;
    }

    public void isCompleted() {
        this.completed = true;
    }

    public String completedBox() {
        if (this.completed) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    boolean getCompleted() {
        return this.completed;
    }

    boolean taskMatch(String keyword) {
        return this.task.contains(keyword);
    }

    // public static Task readTask(String input) {
    //     String[] list = input.split(",", 3);
    //     return new Task(list[1], Boolean.parseBoolean(list[2]));
    // }

    public String toCommand() {
        return this.getClass().toString() + ", " + this.getCompleted() + ", " + this.task;
    }

    @Override
    public String toString() {
        return this.completedBox() + this.task;
    }
}
