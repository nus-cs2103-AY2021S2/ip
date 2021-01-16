public class Response {
    public static int numTasks = 0;
    public static Task[] tasks = new Task[100]; // Array of tasks
    public static String line = "---------------------------";

    public static void myPrint(String str) {
        System.out.println(line + "\n" + str + "\n" + line + "\n");
    }

    public static void hello() {
        myPrint("what's up? :)");
    }

    public static void bye() {
        myPrint("bye, have a good day! :)");
    }

    public static void listTasks() {
        System.out.println(line);

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println(i + ". " + tasks[i - 1].toString());
        }

        System.out.println(line + "\n");
    }

    public static void addTask(Task task) {
        System.out.println(line + "\nokay, added this task:");
        tasks[numTasks] = task;
        numTasks++;
        System.out.println(task.toString());
        System.out.println("you now have a total of " + numTasks + " tasks.");
        System.out.println(line + "\n");
    }

    public static void markAsDone(int num) {
        System.out.println(line + "\nokay, this task has been marked as done:");
        Task currentTask = tasks[num - 1];
        currentTask.markAsDone();
        System.out.println(num + ". " + currentTask.toString());
        System.out.println(line + "\n");
    }
}
