import java.util.ArrayList;
import java.util.List;

public class Task {
    private static List<Task> taskList = new ArrayList<>();
    private String taskName;
    private static int capacity = 0;
    private int index;
    private String done;
    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.done = " ";
        capacity++;
        add(this);
    }

    private static void add(Task t) {
        taskList.add(t);
    }


    public static final void done(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.get(i - 1).done = "X";
            System.out.println(Format.UPPER + "Wah~ You done the task: "
                    + " " + t.toString() + Format.LOWER);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }



    public String getTaskName() {
        return taskName;
    }

    public static final List<Task> getTaskList() {
        return Task.taskList;
    }

    public String isDone() {
        return done;
    }

    public int getIndex() {
        return index;
    }

    public static final int getCapacity() {
        return Task.capacity;
    }
    @Override
    public String toString() {
        return String.format("[%s] %d. %s", done, index, taskName);
    }
}
