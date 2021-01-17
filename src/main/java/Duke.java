import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static ArrayList<Task> toDoList;
    private static void processCommand(String command) {
        if (command.equals("list")) {
            System.out.println("This is your to-do list:");
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println((i+1) + ". " + toDoList.get(i));
            }
        } else {
            System.out.println("Added to to-do list: " + command);
            Task newTask = new Task(command);
            toDoList.add(newTask);
        }
    }

    public static void main(String[] args) {
        toDoList = new ArrayList<Task>();
        Scanner sc =  new Scanner(System.in);
        System.out.println("Greetings. My name is I-01B, but you may call me CHEF. What can I assist you with?");
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("I hope I have been of assistance. Goodbye. C:");
                break;
            } else {
                processCommand(command);
            }
        }
    }
}
