import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list = new ArrayList<>(100);

    public void add(String task) {
        list.add(new Task(list.size() + 1, task));
        System.out.println("added: " + task + "\n");
    }

    public void done(int taskId) {
        if (taskId < list.size()) {
            Task completedTask = list.get(taskId);
            completedTask.markComplete();
            System.out.println("Nice! I've marked this task as done:\n" + completedTask + "\n");
        }
    }

    public void printList(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println("");
    }
}
