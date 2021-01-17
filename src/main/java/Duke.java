import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static ArrayList<Task> ls = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
        System.out.print("User (Enter an input): ");
        String userInput = sc.nextLine();
        while (!"bye".equals(userInput)) {
            if ("list".equals(userInput)) {
                Duke.printList();
            } else if (userInput.contains("done")) {
                int indexToMark = Integer.parseInt(userInput.substring(5));
                Duke.setAsDone(indexToMark);
            } else {
                Duke.addTask(userInput);
            }
            System.out.print("User (Enter an input): ");
            userInput = sc.nextLine();
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i=1; i<=Duke.ls.size(); i++) {
            System.out.println("  " + i + ". " + Duke.ls.get(i-1));
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void setAsDone(int i) {
        Task ts = Duke.ls.get(i-1);
        ts.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + ts);
        System.out.println("-----------------------------------------------------");
    }

    public static void addTask(String userInput) {
        if (userInput.contains("todo")) {
            String[] splits = userInput.split("todo ");
            Todo addedTask = new Todo(Arrays.asList(splits).get(1));
            Duke.ls.add(addedTask);
            System.out.println("Got it, I've added this task to the list: ");
            System.out.println("  " + addedTask);
        } else if (userInput.contains("deadline")) {
            String[] splits = userInput.split("deadline |/by ");
            Deadline addedTask = new Deadline(Arrays.asList(splits).get(1), Arrays.asList(splits).get(2));
            Duke.ls.add(addedTask);
            System.out.println("Got it, I've added this task to the list: ");
            System.out.println("  " + addedTask);
        } else if (userInput.contains("event")) {
            String[] splits = userInput.split("event |/at ");
            Event addedTask = new Event(Arrays.asList(splits).get(1), Arrays.asList(splits).get(2));
            Duke.ls.add(addedTask);
            System.out.println("Got it, I've added this task to the list: ");
            System.out.println("  " + addedTask);
        }

        System.out.println("Now you have " + Duke.ls.size() + " tasks in the list.");
        System.out.println("-----------------------------------------------------");

    }

}
