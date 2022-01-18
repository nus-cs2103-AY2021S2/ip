import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void addTask(String description) {
        this.tasks.add(new Task((description)));
    }

    public String getTaskList() {
        StringBuffer result = new StringBuffer();
        for (int i = 1; i <= tasks.size(); i++) {
            result.append(i);
            result.append(". ");
            result.append(tasks.get(i-1).toString());
            if (i != tasks.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

}
