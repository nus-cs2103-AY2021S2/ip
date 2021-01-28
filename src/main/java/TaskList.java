import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Collection<Task> taskCollection) {
        this.tasks = new ArrayList<>(taskCollection);
    }

    public void printAllTask(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.printTask(i + 1 + ".", tasks.get(i).toString());
        }
    }

    public boolean checkValidOption(Ui ui, int option) {
        boolean result = option < 0 || option >= this.tasks.size();
        if (result) {
            ui.showError("Invalid task Option");
        }
        return result;
    }

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

    public void deleteTask(Ui ui, int option) {
        if(!checkValidOption(ui, option)) {
            Task t = tasks.remove(option);
            ui.showSuccessDeleteTask(t.toString(), tasks.size());
        }
    }

    public void addTask(Ui ui, Task task) {
        tasks.add(task);
        ui.showSuccessAddTask(task.toString(), tasks.size());
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
