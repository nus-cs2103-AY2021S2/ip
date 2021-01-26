import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(Collection<Task> taskCollection) {
        this.taskList = new ArrayList<>(taskCollection);
    }

    public void printAllTask(Ui ui) {
        for (int i = 0; i < taskList.size(); i++) {
            ui.printTask(i + 1, taskList.get(i).toString());
        }
    }

    public boolean checkValidOption(int option) {
        return option < 0 || option > this.taskList.size();
    }

    public boolean markAsDone(int option) {
        if(checkValidOption(option)) {
            Task task = taskList.get(option);
            return task.markAsDone();
        }
        return false;
    }
}
