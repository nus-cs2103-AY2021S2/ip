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
        System.out.println("Got it! Added: \n" + task);
        System.out.println("Now you have " + numOfTasks() + " items in your list");
    }

    //Delete a task
    public void DeleteTask(int idx) {
        System.out.println("Noted. Task removed: \n" + Tasks.get(idx - 1));
        Tasks.remove(idx - 1);
        System.out.println("Now you have " + numOfTasks() + " items in your list");
    }

    //Marks a task as done
    public void markAsDone(int idx) {
        Tasks.get(idx - 1).markDone();
        System.out.println("Task " + idx + " is complete:\n" + Tasks.get(idx - 1));
    }

    //Retrieves the number of tasks
    public int numOfTasks() {
        return Tasks.size();
    }

    public Task getTask(int idx) {
        return Tasks.get(idx);
    }

    //Prints all the tasks
    public void printTasks() {
        System.out.println("Contents: ");
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j) instanceof TodoTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else if (Tasks.get(j) instanceof DeadlineTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else if (Tasks.get(j) instanceof EventTask) {
                System.out.println(j + 1 + "." + Tasks.get(j));
            } else {
                System.out.println(j + 1 + "." + Tasks.get(j));
            }
        }
    }
}
