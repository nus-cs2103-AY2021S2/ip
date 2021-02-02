import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            String input = sc.nextLine();

            if (input.contains(" ") && input.split(" ")[0].equals("done")) {
                int indexOfDoneTask = Integer.parseInt(input.split(" ")[1]);
                Task doneTask = tasks.getTaskByIndex(indexOfDoneTask);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask.getStatusString());
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                tasks.printTasks();
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                Task newTask = new Task(input);
                tasks.addTask(newTask);
                System.out.println("Added: " + newTask.getDescription());
            }
        }
    }
}
