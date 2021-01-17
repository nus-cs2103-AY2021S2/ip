import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static List<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        
        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "bye":
                    printMessage("Bye! I shall go rest now. PAge me when you need me!");
                    return;
                case "list":
                    listTasks();
                break;
                default:
                    addTask(input);
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String task) {
        tasks.add(task);
        printMessage("Task added: " + task);
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1)+". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
