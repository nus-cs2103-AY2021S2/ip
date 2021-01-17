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
        List<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while(!command.equals("bye")) {
            if(command.equals("list")) {
                int count = 1;
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                for(String string: tasks) {
                    System.out.println("  " + count + ". " + string);
                    count++;
                }
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            } else {
                System.out.println("\n~ added: " + command + " ~\n");
                tasks.add(command);
            }
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}
