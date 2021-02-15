import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;
    protected int size;

    /**
     * Constructor to create a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Constructor to create the previous TaskList
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.size = list.size();
    }

    /**
     * Mark the numbered task as done.
     *
     * @param num task number.
     */
    public void doneTask(int num) {
        list.get(num).markAsDone();
    }

    /**
     * Delete the numbered task from the list of tasks.
     *
     * @param num task number.
     */
    public void deleteTask(int num) {
        size = size - 1;
        list.remove(num);
    }

    /**
     * Add the required task into the list of tasks.
     *
     * @param task description.
     */
    public void addTodo(String task) {
        size = size + 1;
        list.add(new Todo(task));
    }


    /**
     * Add the required task into the list of tasks.
     *
     * @param task description.
     * @param time required for deadline and event.
     */
    public void addDeadline(String task, String time) {
        size = size + 1;
        list.add(new Deadline(task, time));
    }

    /**
     * Add the required task into the list of tasks.
     *
     * @param task description.
     * @param time required for deadline and event.
     */
    public void addEvent(String task, String time) {
        size = size + 1;
        list.add(new Event(task, time));
    }

    /**
     * Show all the tasks in the list.
     *
     * @return listed.
     */
    public String listTask() {
        String listed = "";
        for (int i = 0; i < size; i++) {
            listed  = listed + (i + 1) + "." + list.get(i) + "\n";
        }
        return listed ;
    }

    /**
     * Find tasks with name containing the given keyword.
     *
     * @param task keyword.
     * @return task found.
     */
    public String findTask(String task) {
        String found = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(task)) {
                found = found + (i + 1) + "." + string + "\n";
            }
        }

        return found;
    }

    /**
     * Search for tasks which occur at the given date.
     *
     * @param time date
     * @return task found
     */
    public String searchDateTask(String time) {
        String found = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(time)) {
                found = found + (i + 1) + "." + string + "\n";
            }
        }

        return found;
    }

}
