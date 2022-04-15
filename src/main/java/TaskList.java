import java.util.*;

/**
   * TaskList initiates an internal list within the programme
   * 
   * This is done so that we can easily manipulate the list of task
   * that the user would input,mark as done and delete
   */


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
