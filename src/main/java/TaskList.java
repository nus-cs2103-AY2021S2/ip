import java.util.ArrayList;

public class TaskList {
    private static final String ERROR_TASK_DONE = "Task is already marked done";
    private static final String ERROR_INVALID_OPTION = "Invalid task Option";
    private static final String NO_TASK_ADDED = "There is no task added!";
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskCollection) {
        this.tasks = new ArrayList<>(taskCollection);
    }

    /**
     * Returns a String representation of all the task.
     *
     * @return String , String representation of all the task in taskList.
     */
    public String printAllTask() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += Ui.printTask(i + 1 + ".", tasks.get(i).toString());
        }
        if (output.isEmpty()) {
            return NO_TASK_ADDED;
        }
        return output;
    }

    private String showOptionError () {
        return Ui.showError(ERROR_INVALID_OPTION);
    }

    /**
     * Returns boolean that represent option being valid / invalid.
     *
     * @param option Input for checking
     * @return boolean indicating the validity of index option
     */
    public boolean checkValidOption(int option) {
        boolean result = option < 0 || option >= this.tasks.size();
        return result;
    }

    /**
     * Marks a specific task based on the number given as done.
     *
     * @param option task number entered by the user.
     */
    public String markAsDone(int option) {
        if (!checkValidOption(option)) {
            Task task = tasks.get(option);
            if (!task.markAsDone()) {
                return Ui.showError(ERROR_TASK_DONE);
            } else {
                return Ui.showSuccessMarkDone(task.toString(), tasks.size());
            }
        }
        return showOptionError();
    }

    /**
     * Deletes a task in the taskList based on the number given.
     *
     * @param option task number entered by the user.
     */
    public String deleteTask(int option) {
        if (!checkValidOption(option)) {
            Task t = tasks.remove(option);
            return Ui.showSuccessDeleteTask(t.toString(), tasks.size());
        }
        return showOptionError();
    }

    /**
     * Adds a task into the taskList.
     *
     * @param task Task object to be adding into the taskList.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.showSuccessAddTask(task.toString(), tasks.size());
    }

    /**
     * Prints task that has description containing the search term.
     *
     * @param searchTerm String that contains the search term.
     */
    public String search(String searchTerm) {
        int count = 0;
        String output = "";
        for (Task t : tasks) {
            if (t.getDescription().indexOf(searchTerm) != -1) {
                output += Ui.printTask(++count + ".", t.toString());
            }
        }
        if (count == 0) {
            return Ui.showFailSearch(searchTerm);
        }
        return Ui.showSuccessSearch() + "\n" + output;
    }

    /**
     * Update a task by given field by given value.
     *
     * @param option , task numbering in the taskList.
     * @param field , field to be updated.
     * @param value , value to update the field to.
     * @return String, the result of the update.
     */
    public String update(int option, String field, String value) {
        checkValidOption(option);
        return tasks.get(option).update(field, value);
    }

    /**
     * Returns the TaskList.
     *
     * @return ArrayList of Task , taskList.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
