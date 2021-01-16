import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String CHATBOT_NAME = "Mantaro";
    private static boolean isActive = true;

    private static List<Task> tasks = new ArrayList<>();

    /**
     * Add a task to internal list
     * @param desc Description of the task
     */
    private static void addTask(String desc) {
        Task newTask = new Task(desc);
        tasks.add(newTask);

        System.out.println("___________________________________________________________");
        System.out.printf("added: %s\n", desc);
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Mark a task as completed
     * @param index Index of the completed task
     */
    private static void completeTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task task = tasks.get(index);
            task.markAsDone();

            System.out.println("___________________________________________________________");
            System.out.println("Good job meow, I've marked this task as done:");
            System.out.printf("%d.[%s] %s\n", index + 1, task.getStatusSymbol(), task.getDesc());
            System.out.println("___________________________________________________________\n");
        }
    }

    /**
     * List down all tasks in the internal list
     */
    private static void listTasks() {
        System.out.println("___________________________________________________________");
        System.out.println("Meow, here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.[%s] %s\n", i + 1, task.getStatusSymbol(), task.getDesc());
        }
        System.out.println("___________________________________________________________\n");
    }

    /**
     * Lifecycle of the chatbot
     * @param args
     */
    public static void main(String[] args) {
        // Opening message
        System.out.println("___________________________________________________________");
        System.out.printf("Meow, I'm %s\nWhat can I do for you today?\n", CHATBOT_NAME);
        System.out.println("___________________________________________________________\n");

        // Scan user input as a command
        Scanner scanner = new Scanner(System.in);
        String command = "";

        // Respond to user's commands and exit when user enters bye
        while(isActive) {
            command = scanner.nextLine();

            if(command.matches("^done ((100)|[1-9][0-9]|[0-9])$")) {
                String[] splits = command.split(" ");

                try {
                    completeTask(Integer.valueOf(splits[1]) - 1);
                } catch(IndexOutOfBoundsException e) {
                    if(tasks.size() == 0) {
                        System.out.println("___________________________________________________________");
                        System.out.println("There are no task to be completed");
                        System.out.println("___________________________________________________________\n");
                    } else {
                        System.out.println("___________________________________________________________");
                        System.out.printf("Please enter a valid task index ranging from 1 to %d (inclusive)\n",
                                tasks.size());
                        System.out.println("___________________________________________________________\n");
                    }
                }
            } else if(command.equals("list")) {
                listTasks();
            } else if(command.equals("bye")) {
                isActive = false;
            } else {
                addTask(command);
            }
        }

        // Closing message
        System.out.println("___________________________________________________________");
        System.out.println("Meow. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
}
    }
