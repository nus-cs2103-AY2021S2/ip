import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(Collection<Task> taskCollection) {
        this.taskList = new ArrayList<>(taskCollection);
    }

    public void printAllTask(Ui ui) {
        for (int i = 0; i < taskList.size(); i++) {
            ui.printTask(i + 1 + ".", taskList.get(i).toString());
        }
    }

    public boolean checkValidOption(Ui ui, int option) {
        boolean result = option < 0 || option > this.taskList.size();
        if (!result) {
            ui.showError("Invalid task Option");
        }
        return result;
    }

    public void markAsDone(Ui ui, int option) {
        if(checkValidOption(ui, option)) {
            Task task = taskList.get(option);
            if (!task.markAsDone()) {
                ui.showError("Task is already marked done");
            } else {
                ui.showSuccessMarkDone(task.toString(), taskList.size());
            }
        }
    }

    public void deleteTask(Ui ui, int option) {
        if(checkValidOption(ui, option)) {
            Task t = taskList.remove(option);
            ui.showSuccessDeleteTask(t.toString(), taskList.size());
        }
    }
}
