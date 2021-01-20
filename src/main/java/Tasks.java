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
        System.out.println("added: " + task);
    }

    //Marks a task as done
    public void markAsDone(int idx) {
        Tasks.get(idx - 1).markDone();
        System.out.println("Task " + idx + " is complete:\n" + "[X] " + Tasks.get(idx - 1));
    }

    //Retrieves the number of tasks
    public int numOfTasks() {
        return Tasks.size();
    }

    //Prints all the tasks
    public void printTasks() {
        System.out.println("Contents: ");
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j).getStatus()) {
                System.out.println(j + 1 + ".[X] " + Tasks.get(j));
            } else {
                System.out.println(j + 1 + ".[ ] " + Tasks.get(j));
            }
        }
        System.out.println("Now you have " + numOfTasks() + " items in your list");
    }
}
