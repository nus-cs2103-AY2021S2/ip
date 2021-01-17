import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String BORDER = "-------------------------------------";

    public static void main(String[] args) {
        Duke.printWithBorders("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>(100);

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
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        response.append(i + 1);
                        response.append(".");
                        response.append(task);
                        if (i != tasks.size() - 1) {
                            response.append("\n");
                        }
                    }
                } else if (userInput.startsWith("done")) {
                    // TODO: 17/1/21 Throw exception if no valid choice is entered
                    int userChoice = Integer.valueOf(userInput.split(" ")[1]);
                    Task task = tasks.get(userChoice - 1);
                    task.markComplete();
                    response.append("Nice! I've marked this task as done:\n");
                    response.append(task);
                } else if (userInput.startsWith("delete")) {
                    // TODO: 17/1/21 Throw exception if no valid choice is entered
                    int userChoice = Integer.valueOf(userInput.split(" ")[1]);
                    Task task = tasks.remove(userChoice - 1);
                    response.append("Noted. I've removed this task:\n  ");
                    response.append(task);
                    response.append("\nNow you have ");
                    response.append(tasks.size());
                    response.append(" tasks in the list.");
                } else if (userInput.startsWith("event") ||
                        userInput.startsWith("todo") ||
                        userInput.startsWith("deadline")) {
                    Task task = Task.parseTask(userInput);
                    tasks.add(task);
                    response.append("Got it. I've added this task:\n  added: ");
                    response.append(task);
                    response.append("\nNow you have ");
                    response.append(tasks.size());
                    response.append(" tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Duke.printWithBorders(response.toString());
            } catch (DukeException e) {
                Duke.printWithBorders(e.getMessage());
            }
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
