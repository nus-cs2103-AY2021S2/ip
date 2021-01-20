import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String separator = "____________________________________________________________";
    private static ArrayList<String> tasksList;
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
    private static void addTask(String task) {
        tasksList.add(task);
        counter++;
        String addedMessage = "added: " + task;
        replyFormat(addedMessage);
    }

    // Displays all the tasks in tasksList to the user
    private static void displayTasks(ArrayList<String> tasksList) {
        if (tasksList.size() <= 0) {
            replyFormat("There are no tasks at the moment.");
        } else {
            System.out.println(separator);

            for (int i = 1; i <= tasksList.size(); i++) {
                System.out.println(i + ". " + tasksList.get(i - 1));
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
            if (userInput.equals("list")) {
                displayTasks(tasksList);
            } else {
                addTask(userInput);
            }

            userInput = sc.nextLine();
        }

        bye();
    }
}
