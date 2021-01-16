import java.util.*;

public class Kelbot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Kelbot\n" + "What can I do for you?");
        List<Task> toDoList = new ArrayList<>();
        String input = sc.nextLine();
        String[] commands = input.split(" ");
        while (true) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // List out all the tasks
                for (int i = 1; i <= toDoList.size(); i++) {
                    System.out.println(i + "." + toDoList.get(i - 1));
                }
            } else if (commands[0].equals("done")) {
                // Marks a completed task as done
                int index = Integer.parseInt(commands[1]);
                Task taskToComplete = toDoList.get(index - 1);
                taskToComplete.complete();
                System.out.println("Well done! You have completed this task!");
                System.out.println(taskToComplete);
            } else {
                // Add task to list
                Task newTask = new Task(input);
                toDoList.add(newTask);
                System.out.println("added: " + newTask.getName());
            }
            input = sc.nextLine();
            commands = input.split(" ");
        }
        System.out.println("Bye le Bye!");
    }
}
