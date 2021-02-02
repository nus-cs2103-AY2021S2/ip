import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
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

    public ArrayList<Task> getList() {
        return taskList;
    }

    public void printList() {
        if (taskList.size() == 0) {
            System.out.println("Looks like you have no tasks currently. Add some tasks!");
        }

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task item = taskList.get(i);
            System.out.println((i + 1) + "." + item.toString());
        }
    }
}
