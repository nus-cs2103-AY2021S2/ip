import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        String[] list = new String[100];
        int index = 0;
        while (!command.equals("bye")) {
            String[] tokens = command.split(" ");
            String commandType = tokens[0];
            if (commandType.equals("list")) {
                // SHOW LIST
                for (int i = 0; i < index; i++) {
                    System.out.printf("%d. " + list[i] + "\n", i + 1);
                }
            } else {
                list[index] = command;
                System.out.println("added: " + command);
                index++;
            }
            command = scanner.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
