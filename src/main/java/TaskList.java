import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    public TaskList(List<Task> tasks){
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public Task find(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public int getSize() {
        return tasks.size();
    }

}
