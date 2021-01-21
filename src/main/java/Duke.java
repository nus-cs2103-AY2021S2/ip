import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "\n What can I do for you?");
        start();
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while (true) {
            String curr = sc.nextLine();
            if (curr.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (curr.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task t = list.get(i - 1);
                    System.out.println(i + ". " + t);
                }
            } else if (curr.startsWith("done ")) {
                String num = curr.substring(5);
                int taskNum = Integer.parseInt(num);
                Task updated = list.get(taskNum - 1).setDone();
                list.set(taskNum - 1, updated);
                System.out.println("Nice! I've marked this task as done: \n" + updated);
            } else {
                if (curr.startsWith("todo ")) {
                    String taskName = curr.substring(5);
                    Todo newTask = new Todo(taskName);
                    list.add(newTask);
                    System.out.println("Added " + newTask + " to the task list!");
                } else if (curr.startsWith("deadline ")) {
                    String taskName = curr.substring(9);
                    String by = curr.substring(curr.indexOf("/by") + 4);
                    Deadline newTask = new Deadline(taskName, by);
                    list.add(newTask);
                    System.out.println("Added " + newTask + " to the task list!");
                } else if (curr.startsWith("event ")) {
                    String taskName = curr.substring(6);
                    String at = curr.substring(curr.indexOf("/at") + 4);
                    Event newTask = new Event(taskName, at);
                    list.add(newTask);
                    System.out.println("Added " + newTask + " to the task list!");
                }
                System.out.println("You now have " + list.size() + " items in the list");
            }
        }
    }
}
