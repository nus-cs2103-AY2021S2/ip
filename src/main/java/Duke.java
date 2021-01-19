import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = scanner.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}

