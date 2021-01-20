import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String separator = "____________________________________________________________";
    private static ArrayList<Task> tasksList;
    private static int counter;
    private static TaskType taskType;

    public enum TaskType {
        TODO, DEADLINE, EVENT, OTHER;
    }

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
    private static void addTask(String promptDescription) {
        String[] strings;
        String systemMessage;
        String description;
        String period;
        Task newTask;

        switch (taskType) {
            case TODO:
                counter++;
                description = promptDescription;
                newTask = new ToDo(description);
                tasksList.add(newTask);
                systemMessage = "Got it. I've added this task:\n" +  "  " + newTask + "\n"
                                        + "Now you have " + counter + " tasks in the list.";
                break;

            case DEADLINE:
                counter++;
                strings = promptDescription.split(" /by ");
                description = strings[0];
                period = strings[1];
                newTask = new Deadline(description, period);
                tasksList.add(newTask);
                systemMessage = "Got it. I've added this task:\n" + "  " + newTask + "\n"
                                        + "Now you have " + counter + " tasks in the list.";
                break;

            case EVENT:
                counter++;
                strings = promptDescription.split(" /at ");
                description = strings[0];
                period = strings[1];
                newTask = new Event(description, period);
                tasksList.add(newTask);
                systemMessage = "Got it. I've added this task:\n" + "  " + newTask + "\n"
                                        + "Now you have " + counter + " tasks in the list.";
                break;

            default:
                systemMessage = "Task type not recognised. List of recognised task types:\n"
                                    + "1. todo (E.g. todo borrow book)\n"
                                        + "2. deadline (E.g. deadline return book /by Sunday\n"
                                            + "3. event (E.g. event project meeting /at Mon 2-4pm";
                break;
        }

        replyFormat(systemMessage);
    }

    // Marks a task as done and informs the user about it
    private static void completeTask(int taskNo) {
        if (taskNo > 0 & taskNo <= tasksList.size()) {
            Task taskToComplete = tasksList.get(taskNo - 1);
            taskToComplete.markAsDone();
            String doneMessage = "Nice! I've marked this task as done:\n" + "  " + taskToComplete;
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
                System.out.println(i + ". " + currentTask);
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
            String[] inputArray = userInput.split(" ", 2);
            String prompt = inputArray[0];
            String promptDescription = "";

            if (inputArray.length > 1) {
                promptDescription = inputArray[1];
            }

            if (prompt.equals("list")) {
                displayTasks(tasksList);
            } else if (prompt.equals("done")) {
                completeTask(Integer.valueOf(inputArray[1]));
            } else {
                if (prompt.equals("todo")) {
                    taskType = TaskType.TODO;
                } else if (prompt.equals("deadline")) {
                    taskType = TaskType.DEADLINE;
                } else if (prompt.equals("event")) {
                    taskType = TaskType.EVENT;
                } else {
                    taskType = TaskType.OTHER;
                }

                addTask(promptDescription);
            }

            taskType = TaskType.OTHER;
            userInput = sc.nextLine();
        }

        bye();
    }
}
