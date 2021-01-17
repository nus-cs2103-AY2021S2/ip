import java.util.Scanner;

public class Duke {
    private static final String BORDER = "-------------------------------------";

    public static void main(String[] args) {
        Duke.printWithBorders("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            StringBuilder response = new StringBuilder();
            try {
                if (userInput.equals("bye")) {
                    response.append("Bye. Hope to see you soon!");
                    Duke.printWithBorders(response.toString());
                    return;
                } else if (userInput.equals("list")) {
                    response.append("Here are the tasks in your list:\n");
                    for (int i = 0; i < numberOfTasks; i++) {
                        Task task = tasks[i];
                        response.append(i + 1);
                        response.append(".");
                        response.append(task);
                        if (i != numberOfTasks - 1) {
                            response.append("\n");
                        }
                    }
                } else if (userInput.startsWith("done")) {
                    int userChoice = Integer.valueOf(userInput.split(" ")[1]);
                    Task task = tasks[userChoice - 1];
                    task.markComplete();
                    response.append("Nice! I've marked this task as done:\n");
                    response.append(task);
                } else if (userInput.startsWith("event") ||
                        userInput.startsWith("todo") ||
                        userInput.startsWith("deadline")) {
                    Task task = Task.parseTask(userInput);
                    tasks[numberOfTasks++] = task;
                    response.append("Got it. I've added this task:\n  added: ");
                    response.append(task);
                    response.append("\nNow you have ");
                    response.append(numberOfTasks);
                    response.append(" tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Duke.printWithBorders(response.toString());
            } catch (DukeException e) {
                Duke.printWithBorders(e.toString());
            }
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
