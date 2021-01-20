import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String separator = "____________________________________________________________";
    private static ArrayList<Task> tasksList;
    private static int counter;

    private static void replyFormat(String reply) {
        System.out.println(separator + "\n" + reply + "\n" + separator);
    }

    private static void greet() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        replyFormat(greeting);
    }

    private static void bye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        replyFormat(byeMessage);
    }

    // Adds a task to tasksList
    private static void addTask(String userInput) {
        Task newTask = new Task(userInput);
        tasksList.add(newTask);
        counter++;
        String addedMessage = "added: " + newTask.description;
        replyFormat(addedMessage);
    }

    // Marks a task as done and informs the user about it
    private static void completeTask(int taskNo) {
        if (taskNo > 0 & taskNo <= tasksList.size()) {
            Task taskToComplete = tasksList.get(taskNo - 1);
            taskToComplete.markAsDone();
            String doneMessage = "Nice! I've marked this task as done:\n" + "  "
                    + taskToComplete.getStatusIcon() + " " + taskToComplete.description;
            replyFormat(doneMessage);
        } else {
            replyFormat("Task " + taskNo + " is not in the task list!");
        }
    }

    // Displays all the tasks in tasksList to the user
    private static void displayTasks(ArrayList<Task> tasksList) {
        if (tasksList.size() <= 0) {
            replyFormat("There are no tasks at the moment.");
        } else {
            System.out.println(separator);
            System.out.println("Here are the tasks in your list:");

            for (int i = 1; i <= tasksList.size(); i++) {
                Task currentTask = tasksList.get(i - 1);
                System.out.println(i + ". " + currentTask.getStatusIcon()
                                            + " " + currentTask.description);
            }

            System.out.println(separator + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasksList = new ArrayList<>();
        counter = 0;

        greet();
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String[] inputArray = userInput.split(" ");
            String prompt = inputArray[0];

            if (prompt.equals("list")) {
                displayTasks(tasksList);
            } else if (prompt.equals("done")) {
                completeTask(Integer.valueOf(inputArray[1]));
            } else {
                addTask(userInput);
            }

            userInput = sc.nextLine();
        }

        bye();
    }
}
