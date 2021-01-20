import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
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
        while(!command.equals("bye")) {
            if (command.equals("list")) {
                int count = 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Here are the tasks in your list:");
                for (Task task : tasks) {
                        System.out.println("  " + count + ".[" + task.getStatusIcon() +"] " + task.getDescription());
                    count++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if(command.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Good job, I have marked this task as done!");
                System.out.println("[X] " + tasks.get(index).getDescription());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                tasks.get(index).markAsDone();
            } else {
                System.out.println("\n~ added: " + command + " ~\n");
                tasks.add(new Task(command));
            }
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}
