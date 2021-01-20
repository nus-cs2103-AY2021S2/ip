import java.util.ArrayList;

public class Tasks {
    ArrayList<Task> Tasks;

    //Initializes the tasklist
    Tasks() {
        Tasks = new ArrayList<Task>();
    }

    //Adds a task to the task list.
    public void addTask(Task task) {
        Tasks.add(task);
        System.out.println("added: " + task.getTaskName() + "\n");
    }

    //Marks a task as done
    public void markAsDone(int idx) {
        Tasks.get(idx - 1).markDone();
        System.out.println("Task " + idx + " is complete:\n" + "[X] " + Tasks.get(idx - 1).getTaskName());
    }

    //Prints all the tasks
    public void printTasks() {
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j).getStatus()) {
                System.out.println(j + 1 + ".[X] " + Tasks.get(j).getTaskName() + "\n");
            } else {
                System.out.println(j + 1 + ".[ ] " + Tasks.get(j).getTaskName() + "\n");
            }
        }
    }
}
