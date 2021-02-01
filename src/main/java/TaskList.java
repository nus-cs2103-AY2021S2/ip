import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;
    protected int size;

    /**
     * Contructor to create a new TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Contructor to create the previous TaskList
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
        Task t = list.get(num);
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
     * Show all the tasks in the list.
     */
    public void listTask() {
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    public String listStrTask() {
        String respone = "";
        for (int i = 0; i < size; i++) {
            respone = respone + (i + 1) + "." + list.get(i) + "\n";
        }
        return respone;
    }

    /**
     * Find the tasks which has similar name to the string.
     *
     * @param task string to be searched.
     */
    public void findTask(String task) {
        int num = 1;

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(task)) {
                System.out.println(num++ + "." + string);
            }
        }
    }

    /**
     * Find the tasks which has similar name to the string.
     *
     * @param task
     * @return task found
     */
    public String findStrTask(String task) {
        int num = 1;
        String respone = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(task)) {
                respone = respone + num++ + "." + string + "\n";
            }
        }
        return respone;
    }

    /**
     * Search for tasks which take place at that timing.
     *
     * @param time string to be searched.
     */
    public void dateTask(String time) {
        int num = 1;

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(time)) {
                System.out.println(num++ + "." + string);
            }
        }
    }

    /**
     * Search for tasks which take place at that timing.
     *
     * @param time
     * @return task found
     */
    public String dateStrTask(String time) {
        int num = 1;
        String respone = "";

        for (int i = 0; i < size; i++) {
            String string = list.get(i).toString();
            if (string.contains(time)) {
                respone = respone + num++ + "." + string + "\n";
            }
        }
        return respone;
    }

}
