import java.util.*;

public class Duke {
    public static ArrayList<Task> myTasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList();
            } else {
                String[] arr = input.split(" ");
                if (arr[0].equals("done")) {
                    markAsDone(Integer.parseInt(arr[1]));
                } else {
                    addNewTask(input);
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void markAsDone(int taskNum) {
        Task t = myTasks.get(taskNum - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        String icon = t.getStatusIcon();
        System.out.println(icon + t);
    }

    public static void addNewTask(String name) {
        myTasks.add(new Task(name));
        System.out.println("added: " + name);
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= myTasks.size(); i++) {
            Task t = myTasks.get(i - 1);
            System.out.println(i + "." + t.getStatusIcon() + t);
        }
    }
    public static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}
