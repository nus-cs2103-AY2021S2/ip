import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> todoList;
    private static void processCommand(String command) {
        String strippedCommand = command.strip();
        if (strippedCommand.equals("list")) {
            System.out.println("This is your to-do list:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i+1) + ". " + todoList.get(i));
            }
        } else if (strippedCommand.startsWith("done")) {
            String indexString = strippedCommand.substring(5).strip();
            int index = Integer.parseInt(indexString) - 1;
            Task toDo = todoList.get(index);
            toDo.doTask();
            System.out.println("Affirmative. The following task has been marked as done: \n" + toDo);
        } else if (strippedCommand.startsWith("todo")) {
            String cmd = strippedCommand.substring(5).strip();
            Task newTask = new Todo(cmd);
            todoList.add(newTask);
            System.out.println("Added to to-do list: \n" + newTask);
        } else if (strippedCommand.startsWith("deadline")) {
            String cmd = strippedCommand.substring(9).strip();
            String[] split = cmd.split("/by");
            Task newTask = new Deadline(split[0].strip(), split[1].strip());
            todoList.add(newTask);
            System.out.println("Added to to-do list: \n" + newTask);
        } else if (strippedCommand.startsWith("event")) {
            String cmd = strippedCommand.substring(6).strip();
            String[] split = cmd.split("/at");
            Task newTask = new Event(split[0].strip(), split[1].strip());
            todoList.add(newTask);
            System.out.println("Added to to-do list: \n" + newTask);
        } else {
            //error handling
        }
    }

    public static void main(String[] args) {
        todoList = new ArrayList<>();
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
