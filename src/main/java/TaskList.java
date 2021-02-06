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
     * @param action command.
     * @param task description.
     * @param time required for deadline and event.
     */
    public void addTask(String action, String task, String time) {
        size = size + 1;

        if (action.equals("todo")) {
            list.add(new Todo(task));
        } else if (action.equals("deadline")) {
            list.add(new Deadline(task, time));
        } else if (action.equals("event")) {
            list.add(new Event(task, time));
        }
    }

    /**
     * Add the required task into the list of tasks.
     *
     * @param action command.
     * @param task description.
     */
    public void addTodo(String action, String task) {
        size = size + 1;
        list.add(new Todo(task));
    }


    /**
     * Add the required task into the list of tasks.
     *
     * @param action command.
     * @param task description.
     * @param time required for deadline and event.
     */
    public void addDeadline(String action, String task, String time) {
        size = size + 1;
        list.add(new Deadline(task, time));
    }

    /**
     * Add the required task into the list of tasks.
     *
     * @param action command.
     * @param task description.
     * @param time required for deadline and event.
     */
    public void addEvent(String action, String task, String time) {
        size = size + 1;
        list.add(new Event(task, time));
    }

    /**
     * Show all the tasks in the list.
     */
    public String listTask() {
        String response = "";
        for (int i = 0; i < size; i++) {
            response = response + (i + 1) + "." + list.get(i) + "\n";
        }
        return response;
    }

    /**
     * Find the tasks which has similar name to the string.
     *
     * @param task string
     * @return task found
     */
    public String findTask(String task) {
        int num = 1;
        String response = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(task)) {
                response = response + num++ + "." + string + "\n";
            }
        }
        return response;
    }

    /**
     * Search for tasks which take place at that timing.
     *
     * @param time date
     * @return task found
     */
    public String searchDateTask(String time) {
        int num = 1;
        String response = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(time)) {
                response = response + num++ + "." + string + "\n";
            }
        }
        return response;
    }

}
