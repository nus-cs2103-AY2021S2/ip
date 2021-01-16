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
            System.out.println(i + ". [" + tasks[i - 1].getStatusIcon()
                    + "] " + tasks[i - 1].description);
        }

        System.out.println(line + "\n");
    }

    public static void addTask(String input) {
        myPrint("added: " + input);
        tasks[numTasks] = new Task(input);
        numTasks++;
    }

    public static void markAsDone(int num) {
        System.out.println(line + "\nokay, this task has been marked as done:");
        Task currentTask = tasks[num - 1];
        currentTask.markAsDone();
        System.out.println(num + ". [" + currentTask.getStatusIcon()
                + "] " + currentTask.description);
        System.out.println(line + "\n");
    }
}
