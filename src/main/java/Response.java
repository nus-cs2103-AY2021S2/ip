import java.util.ArrayList;

public class Response {
    public static int numTasks = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();
    //public static Task[] tasks = new Task[100]; // Array of tasks
    //public static String line = "---------------------------";

//    public static void myPrint(String str) {
//        System.out.println(line + "\n" + str + "\n" + line + "\n");
//    }

    public static void hello() {
        System.out.println("\nwhat's up? :)\n");
    }

    public static void bye() {
        System.out.println("\nbye, have a good day! :)\n");
    }

    public static void listTasks() {
        System.out.println("");

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }

        System.out.println("");
    }

    public static void addTask(Task task) {
        System.out.println("\nokay, added this task:");
        tasks.add(numTasks, task); // [numTasks] = task;
        numTasks++;
        System.out.println(task.toString());
        System.out.println("you now have a total of " + numTasks + " tasks.\n");
        //System.out.println(line + "\n");
    }

    public static void markAsDone(int num) {
        System.out.println("\nokay, this task has been marked as done:");
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();
        System.out.println(num + ". " + currentTask.toString() + "\n");
        //System.out.println("\n");
    }
}
