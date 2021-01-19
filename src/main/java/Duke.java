import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Task[] list = new Task[100];
        int index = 0;
        while (!command.equals("bye")) {
            String[] tokens = command.split(" ");
            String commandType = tokens[0];
            if (commandType.equals("list")) {
                // SHOW LIST
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.printf("%d. " + list[i] + "\n", i + 1);
                }
            } else if (commandType.equals("done")){
                    // MARK TASK AS COMPLETE
                    int taskIndex = Integer.parseInt(tokens[1]) - 1;
                    list[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" +  list[taskIndex].toString());
            } else {
                list[index] = new Task(command);
                System.out.println("added: " + list[index]);
                index++;
            }
            command = scanner.nextLine();
        }
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}
