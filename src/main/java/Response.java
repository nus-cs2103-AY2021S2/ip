public class Response {
    public static int numTasks = 0;
    public static String[] tasks = new String[100]; // Array of tasks
    public static String line = "---------------------------";

    public static void myPrint(String str) {
        System.out.println(line + "\n" + str + "\n" + line + "\n");
    }
    public static void hello() {
        myPrint("what's up? :)");
    }

    public static void listTasks() {
        System.out.println(line);

        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println( i + ". " + tasks[i - 1]);
        }

        System.out.println(line + "\n");
    }

    public static void addTask(String input) {
        myPrint("added: " + input);
        tasks[numTasks] = input;
        numTasks++;
    }
}
