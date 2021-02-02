import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Collection<Task> taskCollection) {
        this.tasks = new ArrayList<>(taskCollection);
    }

    /**
     * Provides UI object the representation
            of all the task in the taskList for printing
     * @param ui (Ui object to do the actual printing)
     */
    public void printAllTask(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.printTask(i + 1 + ".", tasks.get(i).toString());
        }
    }

    /**
     * Returns boolean that represent option being valid / invalid
     * @param ui UI object for printing error message
     * @param option Input for checking
     * @return
     */
    public boolean checkValidOption(Ui ui, int option) {
        boolean result = option < 0 || option >= this.tasks.size();
        if (result) {
            ui.showError("Invalid task Option");
        }
        return result;
    }

    /**
     * Marks a specific task based on the number given as done
     * @param ui Ui object to inform user if the task is already done
     * @param option task number entered by the user
     */
    public void markAsDone(Ui ui, int option) {
        if(!checkValidOption(ui, option)) {
            Task task = tasks.get(option);
            if (!task.markAsDone()) {
                ui.showError("Task is already marked done");
            } else {
                ui.showSuccessMarkDone(task.toString(), tasks.size());
            }
        }
    }

    /**
     * Deletes a task in the taskList based on the number given
     * @param ui Ui object to show message upon successfully deleting
     * @param option task number entered by the user
     */
    public void deleteTask(Ui ui, int option) {
        if(!checkValidOption(ui, option)) {
            Task t = tasks.remove(option);
            ui.showSuccessDeleteTask(t.toString(), tasks.size());
        }
    }

    /**
     * Adds a task into the taskList
     * @param ui UI object to show message upon successfully adding
     * @param task Task object to be adding into the taskList
     */
    public void addTask(Ui ui, Task task) {
        tasks.add(task);
        ui.showSuccessAddTask(task.toString(), tasks.size());
    }

    /**
     * Prints task that has description containing the search term
     * @param ui Ui object that does the printing
     * @param searchTerm String that contains the serach term
     */
    public void search(Ui ui, String searchTerm) {
        int count = 0;
        for(Task t : tasks) {
            if (t.getDescription().indexOf(searchTerm) != -1) {
                if(count == -1) {
                    ui.showSuccessSearch();
                }
                ui.printTask(++count + ".", t.toString());
            }
        }
        if (count == 0) {
            ui.showFailSearch(searchTerm);
        }
    }

    /**
     * Returns the TaskList
     * @return ArrayList<Task> taskList
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
