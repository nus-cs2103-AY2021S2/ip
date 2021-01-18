import java.util.ArrayList;

/**
 * The Command class handles the logic of all allowed user commands for Duke.
 */
public class Command {

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("Bye! See you later :D");
    }

    /**
     * Parses, adds and prints the input from user as task.
     * @param input input provided by user
     */
    public static void add(String input, ArrayList<Task> tasks) {
        //split input on first space to retrieve task type
        String[] parsedString = input.split("\\s+", 2);
        Task task;

        //attempt to create a task, inform user if input is invalid
        try {
            String taskType = parsedString[0];
            String taskDetails = parsedString[1];

            String taskName;
            String[] parsedTaskDetails;
            switch (taskType) {
                case "todo":
                    taskName = taskDetails.trim();
                    task = new ToDo(Task.numTasks + 1, taskName, "incomplete");
                    break;
                case "deadline":
                    parsedTaskDetails = taskDetails.split("/by");
                    taskName = parsedTaskDetails[0].trim();
                    String endDate = parsedTaskDetails[1].trim();
                    task = new Deadline(Task.numTasks + 1, taskName, "incomplete", endDate);
                    break;
                case "event":
                    parsedTaskDetails = taskDetails.split("/at");
                    taskName = parsedTaskDetails[0].trim();
                    String startEndDate = parsedTaskDetails[1].trim();
                    task = new Event(Task.numTasks + 1, taskName, "incomplete", startEndDate);
                    break;
                default:
                    System.out.println("Invalid task input!");
                    return;
            }
            tasks.add(task);
            System.out.print("Got it! I have added this task:\n" + task + "\nYou have " + Task.numTasks + " task(s) now!\n");
        } catch (Exception e) {
            System.out.println("Invalid task input!");
        }
    }

    /**
     * List all tasks entered by user.
     */
    public static void list(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + "." + task);
        }
    }

    /**
     * Checks and marks given task as done and informs user of success/failure.
     * @param input input provided by user
     */
    public static void done(String input, ArrayList<Task> tasks) {
        String[] parsedString = input.split("\\s+");
        try {
            int taskId = Integer.parseInt(parsedString[1]);
            Task task = tasks.get(taskId - 1);
            if (task.getStatus().equals("complete")) {
                System.out.println("Task is already completed!");
            } else {
                task.markCompleted();
                System.out.println("Yay your task is done! :D");
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}
