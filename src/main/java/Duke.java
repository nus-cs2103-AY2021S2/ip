import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String formatLine = "************************************";

    public static void greetUser() {
        System.out.println(formatLine + "\nHey, " +
                "I am Duke.\nHow can I help you?\n" +
                formatLine);
    }

    public static void farewellUser() {
        System.out.println(formatLine +
                "\nGoodbye and see you soon!\n" +
                formatLine);
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println(formatLine);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.description);

        }
        System.out.println(formatLine);

    }

    public static void markAsDone(ArrayList<Task> tasks, int index) {
        Task task  = tasks.get(index);
        task.markAsDone();
        System.out.println(formatLine);
        System.out.println(" Good job! I've marked this task as done: \n"+
                "   [" + task.getStatusIcon() + "] " + task.description);
        System.out.println(formatLine);

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        greetUser();
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                listTasks(tasks);
            }
            else if(input.startsWith("done")){
                int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                markAsDone(tasks, index-1);
            }
            else {
                System.out.println("   >>> " + input);
                tasks.add(new Task(input));
            }
            input = sc.nextLine();

        }
        farewellUser();


    }
}
