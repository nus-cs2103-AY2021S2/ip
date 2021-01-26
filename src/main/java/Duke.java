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
                exit();
                break;
            } else if (curr.equals("list")) {
                listTasks(list);
            } else if (curr.startsWith("done ")) {
                done(curr, list);
            } else if (curr.startsWith("delete ")) {
                delete(curr, list);
            } else if (curr.startsWith("todo")) {
                createTodo(curr, list);
            } else if (curr.startsWith("deadline")) {
                createDeadline(curr, list);
            } else if (curr.startsWith("event")) {
                createEvent(curr, list);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listTasks(ArrayList<Task> list) {
        for (int i = 1; i <= list.size(); i++) {
            Task t = list.get(i - 1);
            System.out.println(i + ". " + t);
        }
    }

    public static void done(String curr, ArrayList<Task> list) {
        String num = curr.substring(5);
        int taskNum = Integer.parseInt(num);
        Task updated = list.get(taskNum - 1).setDone();
        list.set(taskNum - 1, updated);
        System.out.println("Nice! I've marked this task as done: \n" + updated);
    }

    public static void delete(String curr, ArrayList<Task> list) {
        String num = curr.substring(7);
        int taskNum = Integer.parseInt(num);
        Task toRemove = list.remove(taskNum - 1);
        System.out.println("Noted. I have removed " + toRemove);
        System.out.println("You now have " + list.size() + " items in the list");
    }

    public static void createTodo(String curr, ArrayList<Task> list) {
        String taskName = curr.substring(4).stripLeading();
        if (taskName.equals("")) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            Todo newTask = new Todo(taskName);
            list.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + list.size() + " items in the list");
        }
    }

    public static void createDeadline(String curr, ArrayList<Task> list) {
        String taskName = curr.substring(8).stripLeading();
        if (taskName.equals("")) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            String by = curr.substring(curr.indexOf("/by") + 4);
            Deadline newTask = new Deadline(taskName, by);
            list.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + list.size() + " items in the list");
        }
    }

    public static void createEvent(String curr, ArrayList<Task> list) {
        String taskName = curr.substring(5).stripLeading();
        if (taskName.equals("")) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            String at = curr.substring(curr.indexOf("/at") + 4);
            Event newTask = new Event(taskName, at);
            list.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + list.size() + " items in the list");
        }
    }
}
