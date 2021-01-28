import java.util.*;
public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>(100);
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    int size() {
        return this.taskList.size();
    }

    void add(Task task) {
        this.taskList.add(task);
    }

    void delete(int index) {
        this.taskList.remove(index);
    }

    void done(int index) {
        Task curTask = this.taskList.get(index);
        curTask.markAsDone();
    }

	public Task get(int i) {
		return this.taskList.get(i);
	}
}
