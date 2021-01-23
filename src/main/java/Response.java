import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Response {

    public static int numTasks = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();
    static {
        try {
            tasks = Save.loadData();
            numTasks = tasks.size();
        } catch (FileNotFoundException e) {
            System.out.println("\nerror: cannot load the save data!");
        }
    }

    public static void hello() {
        System.out.println("\nwhat's up? :)\n");
    }

    public static void bye() {
        System.out.println("\nbye, have a good day! :)\n");
    }

    public static void listTasks() { // Prints out the tasks in the list
        System.out.println("");

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }

        System.out.println("");
    }

    public static void addTask(Task task) { // Add a task to the list
        System.out.println("\nokay, added this task:");
        tasks.add(numTasks, task);
        numTasks++;
        System.out.println(task.toString());
        //saveTaskString = task.saveTask();
        System.out.println("you now have a total of " + numTasks + " tasks.\n");
    }

    public static void markAsDone(int num) { // Marks a task as done
        System.out.println("\nokay, this task has been marked as done:");
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();
        System.out.println(num + ". " + currentTask.toString() + "\n");
    }

    public static void deleteTask(int num) { // Delete a task from the list
        System.out.println("\nokay, deleted this task:");
        System.out.println(tasks.get(num - 1).toString());
        tasks.remove(num - 1);
        numTasks--;
        System.out.println("you now have a total of " + numTasks + " tasks.\n");
    }
}
