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
                addNewTask(input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void addNewTask(String name) {
        myTasks.add(new Task(name));
        System.out.println("added: " + name);
    }

    public static void showList() {
        for (int i = 1; i <= myTasks.size(); i++) {
            System.out.println(i + ". " + myTasks.get(i-1));
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
