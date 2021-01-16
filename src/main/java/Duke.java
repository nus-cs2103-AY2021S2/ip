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
            if (userInput.equals("bye")) {
                Duke.printWithBorders("Bye. Hope to see you soon!");
                return;
            } else if (userInput.equals("list")) {
                String output = "";
                for (int i = 0; i < numberOfTasks; i++) {
                    Task task = tasks[i];
                    String completionStatus = task.isComplete() ? "[X]" : "[ ]";
                    output += (i + 1) + "." + completionStatus + " " + task;
                    if (i != numberOfTasks - 1) {
                        output += "\n";
                    }
                }
                Duke.printWithBorders(output);
            } else if (userInput.startsWith("done")) {
                int userChoice = Integer.valueOf(userInput.split(" ")[1]);
                Task task = tasks[userChoice - 1];
                task.markComplete();
                String output = "Nice! I've marked this task as done:\n";
                output += "  [X] " + task;
                Duke.printWithBorders(output);
            } else {
                tasks[numberOfTasks++] = new Task(userInput);
                String output = "added: " + userInput;
                Duke.printWithBorders(output);
            }
        }
    }

    public static void printWithBorders(String message) {
        System.out.println(Duke.BORDER);
        System.out.println(message);
        System.out.println(Duke.BORDER);
    }
}
