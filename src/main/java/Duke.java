import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> storageList = new ArrayList<>();
        int listCounter;
        String name = "Link";
        String userInput;
        System.out.println("Hello! I'm " + name);
        System.out.println("How are you feeling today?");
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            listCounter = 1;
            String copy = userInput;
            String[] parts = copy.split(" ");
            String keyword = parts[0];
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                while (storageList.size() != listCounter) {
                    System.out.println(listCounter + ". " + storageList.get(listCounter - 1).displayTask());
                    listCounter++;
                }
                System.out.println(listCounter + ". " + storageList.get(listCounter - 1).displayTask());
                userInput = sc.nextLine();
            } else if (keyword.equals("done")) {
                int index = Integer.parseInt(parts[1]);
                System.out.println(storageList.get(index-1).markAsDone());
                userInput = sc.nextLine();
            } else {
                Task task = new Task(userInput);
                storageList.add(task);
                System.out.println("added: " + userInput);
                userInput = sc.nextLine();
            }
        }
        System.out.println("Bye. Talk to me anytime!");
    }
}
