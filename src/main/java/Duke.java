import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        List<Task> store = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            Task newTask = new Task(input);
            System.out.println("    ____________________________________________________________");
            if (parts[0].equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            } else if (parts[0].equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                int count = 1;
                for (Task t: store) {
                    System.out.println("     " + count + "." + t);
                    count++;
                }
            } else if (parts[0].equals("done")) {
                int taskDone = Integer.parseInt(parts[1]);
                int count = 1;
                for (Task t: store) {
                    if (count == taskDone) {
                        System.out.println("     Nice! I've marked this task as done:");
                        store.set(count-1, store.get(count-1).markAsDone());
                        System.out.println("     " + store.get(count-1));
                    }
                    count++;
                }
            } else {
                store.add(newTask);
                System.out.println("     added: " + input);
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
