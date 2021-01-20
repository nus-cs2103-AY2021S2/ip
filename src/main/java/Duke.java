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
        List<String[]> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while(!command.equals("bye")) {
            if (command.equals("list")) {
                int count = 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Here are the tasks in your list:");
                for (String[] task : tasks) {
                    if (task[1].equals("done")) {
                        System.out.println("  " + count + ".[X] " + task[0]);
                    } else {
                        System.out.println("  " + count + ".[ ] " + task[0]);
                    }
                    count++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else if(command.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Good job, I have marked this task as done!");
                System.out.println("[X] " + tasks.get(index)[0]);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                tasks.set(index, new String[]{tasks.get(index)[0], "done"});
            } else {
                System.out.println("\n~ added: " + command + " ~\n");
                tasks.add(new String[]{command, "not done"});
            }
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}
