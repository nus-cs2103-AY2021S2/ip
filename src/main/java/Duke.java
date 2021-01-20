import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        int count = 0;
        String logo =
                "     ______\n" +
                        "    |___  /\n" +
                        "       / / \n" +
                        "      / /  \n" +
                        "     / /__ \n" +
                        "    /_____|\n";
        System.out.println("\n~ Hello! I am Zee :) ~\n"
                + logo + "\n"
                + "~ What can I do for you today? ~\n");
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                int listCount = 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println("  " + listCount + "." + task.toString());
                    listCount++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if (command.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                tasks.get(index).markAsDone();
                System.out.println("Good job, I have marked this task as done!");
                System.out.println(tasks.get(index).toString());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if (command.split(" ")[0].equals("todo")) {
                tasks.add(new Todo(command.substring(5)));
                count++;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks.get(count - 1).toString());
                System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if (command.split(" ")[0].equals("deadline")) {
                tasks.add(new Deadline(command.substring(9).split("/")[0], command.substring(9).split("/")[1].substring(3)));
                count++;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks.get(count - 1).toString());
                System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if (command.split(" ")[0].equals("event")) {
                tasks.add(new Event(command.substring(6).split("/")[0], command.substring(6).split("/")[1].substring(3)));
                count++;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks.get(count - 1).toString());
                System.out.println("Now you have " + count + (count == 1 ? " task" : " tasks") + " in the list.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}