import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> toDoList;
    private static void processCommand(String command) {
        String strippedCommand = command.strip();
        if (strippedCommand.equals("list")) {
            System.out.println("This is your to-do list:");
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println((i+1) + ". " + toDoList.get(i));
            }
        } else if (strippedCommand.startsWith("done")) {
            String indexString = strippedCommand.substring(5).strip();
            int index = Integer.parseInt(indexString) - 1;
            Task toDo = toDoList.get(index);
            toDo.doTask();
            System.out.println("Affirmative. The following task has been marked as done: \n" + toDo);
        } else {
            Task newTask = new Todo(command);
            toDoList.add(newTask);
            System.out.println("Added to to-do list: \n" + newTask);
        }
    }

    public static void main(String[] args) {
        toDoList = new ArrayList<>();
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
