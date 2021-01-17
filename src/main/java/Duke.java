import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String input;
        String splitInput[];
        Scanner scanner = new Scanner(System.in);
        
        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
            input = scanner.nextLine().toLowerCase();
            splitInput = input.split(" ");
            String command = splitInput[0];
            switch (command) {
                case "bye":
                    printMessage("Bye! I shall go rest now. PAge me when you need me!");
                    return;
                case "list":
                    listTasks();
                break;
                case "done":
                    doneTask(Integer.parseInt(splitInput[1]));
                    break;
                default:
                    addTask(new Task(input));
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(Task task) {
        tasks.add(task);
        printMessage("Task added: " + task.getDescription());
    }

    public static void doneTask(int index) {
        tasks.get(index - 1).markAsDone();
        printMessage("Good job! I've marked this task as done:\n"+tasks.get(index - 1));
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1)+". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
