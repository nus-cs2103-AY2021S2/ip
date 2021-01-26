import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task){
        taskList.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " task(s) in the list.");
    }

    public void deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("Gotcha. I've removed this task:\n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " task(s) in the list.");
    }

    public void taskDone(int index) {
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("Nice job! I've marked this task as done:\n"
                + task.toString());
    }

    public int getListSize() {
        return taskList.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task item = taskList.get(i);
            System.out.println((i + 1) + "." + item.toString());
        }
    }
}
