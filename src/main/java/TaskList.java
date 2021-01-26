import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list = new ArrayList<>();
    public TaskList(){
    }

    public Task addTask(Task task) {
        list.add(task);
        return task;
    }

    public Task doTask(int taskNum) {
        Task curr = list.get(taskNum - 1);
        curr.isDone = true;
        return curr;
    }

    public Task delete(int num) throws ArrayIndexOutOfBoundsException {
        Task curr = list.get(num - 1);
        list.remove(num - 1);
        return curr;
    }
    // I'll need to change this later
    public void bye(Duke duke) {
        duke.isOn = false;
    }
}
