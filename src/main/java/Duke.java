import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<Task>();
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
        System.out.print("User (Enter an input): ");
        String userInput = sc.nextLine();
        while (!"bye".equals(userInput)) {
            if ("list".equals(userInput)) {
                System.out.println("Here are the tasks in your list:");
                for (int i=1; i<=ls.size(); i++) {
                    System.out.println("  " + i + ". " + ls.get(i-1));
                }
                System.out.println("-----------------------------------------------------");
            } else if (userInput.contains("done")) {
                int taskToMark = Integer.parseInt(userInput.substring(5));
                ls.get(taskToMark-1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + ls.get(taskToMark-1));
                System.out.println("-----------------------------------------------------");
            } else {
                Task addedTask = new Task(userInput);
                System.out.println("Added to list of tasks: " + userInput);
                System.out.println("-----------------------------------------------------");
                ls.add(addedTask);
            }
            System.out.print("User (Enter an input): ");
            userInput = sc.nextLine();
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }
}
