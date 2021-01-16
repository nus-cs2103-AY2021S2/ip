import java.util.*;

public class Duke {
    public static ArrayList<Task> myTasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println();
            if (input.equals("list")) {
                showList();
            } else {
                taskManager(input);
            }
            System.out.println();
            input = sc.nextLine();
        }
        System.out.println();
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public static void taskManager(String task) {
        String[] line = task.split(" ", 2);
        String type = line[0];
        String[] description = line[1].split(" /");
        if (type.equals("done")) {
            markAsDone(Integer.parseInt(description[0]));
        } else {
            System.out.println("Got it. I've added this task:");
            if (type.equals("todo")) {
                addToDo(description[0]);
            } else if (type.equals("deadline")) {
                String[] by = description[1].split(" ", 2);
                addDeadline(description[0], by[1]);
            } else if (type.equals("event")) {
                String[] at = description[1].split(" ", 2);
                addEvent(description[0], at[1]);
            }
            int numOfTasks = myTasks.size();
            System.out.println("Now you have " + numOfTasks + " tasks in the list");
        }
    }

    public static void markAsDone(int taskNum) {
        Task t = myTasks.get(taskNum - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(t);
    }

    public static void addToDo(String name) {
        Task task = new ToDos(name);
        myTasks.add(task);
        System.out.println(task);
    }

    public static void addDeadline(String name, String by) {
        Task task = new Deadline(name, by);
        myTasks.add(task);
        System.out.println(task);
    }

    public static void addEvent(String name, String at) {
        Task task = new Event(name, at);
        myTasks.add(task);
        System.out.println(task);
    }

    public static void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= myTasks.size(); i++) {
            Task t = myTasks.get(i - 1);
            System.out.println(i + "." + t);
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
