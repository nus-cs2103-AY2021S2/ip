import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();

        while (true) {
            InputHandler handler = new InputHandler(sc.nextLine());

            String action = handler.getAction();

            if (action.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (action.equals("list")) {
                System.out.println("Here are the task(s) in your list:");
                tasks.printTasks();
            }

            if (action.equals("done")) {
                int indexOfDoneTask = Integer.parseInt(handler.getDescription());
                Task doneTask = tasks.getTaskByIndex(indexOfDoneTask);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask.getStatusString());
            }

            if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                String description = handler.getDescription();

                Task newTask;

                if (action.equals("todo")) {
                    newTask = new ToDo(description);
                } else if (action.equals("deadline")) {
                    String by = handler.getBy();
                    newTask = new Deadline(description, by);
                } else {
                    String at = handler.getAt();
                    newTask = new Event(description, at);
                }

                tasks.addTask(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.getStatusString());
                System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
            }
        }
    }
}
