import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructor of TaskList class.
     *
     * @param tasks list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Set a task in taskList.
     * @param i target index.
     * @param task the task
     */

    public void set(int i, Task task) {
        this.tasks.set(i,task);
    }

    /**
     * Look at the i-th task.
     * @param i target index.
     * @return the task
     */

    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Add a task.
     * @param task the task
     */

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task in the i-th index.
     * @param i target index.
     */

    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Find the number of tasks
     * @return number of tasks
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Filter the tasks that match the keyword
     * @param keyword the target keyword.
     * @return the TaskList containing all desired tasks.
     */

    public TaskList find(String keyword) {
        ArrayList<Task> answer = new ArrayList<Task>();
        for(Task i: this.getList()) {
            if(i.getName().contains(keyword)) {
                answer.add(i);
            }
        }
        return new TaskList(answer);
    }

    public TaskList sort(){
        ArrayList<Task> newList = this.getList();
        Collections.sort(newList, (o1,o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return new TaskList(newList);
    }
}
