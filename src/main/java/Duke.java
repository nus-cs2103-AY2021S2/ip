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
                    System.out.println(i + ". [" + t.getStatusIcon() + "] " + t.name);
                }
            } else if (curr.startsWith("done ")) {
                String num = curr.substring(5);
                int taskNum = Integer.parseInt(num);
                Task updated = list.get(taskNum - 1).setDone();
                list.set(taskNum - 1, updated);
                System.out.println("Nice! I've marked this task as done: \n" + "[" + updated.getStatusIcon() + "] " + updated.name);
            } else {
                Task newTask = new Task(curr);
                list.add(newTask);
                System.out.println("added " + curr + " to the task list!");
            }
        }
    }
}
