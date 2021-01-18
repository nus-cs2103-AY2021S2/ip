import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Command class handles the logic of all allowed user commands for Duke.
 */
public class Command {

    //list command usage, ideally store in json file
    private final static HashMap<String, String> cmdInfo = new HashMap<>();

    public Command() {
        cmdInfo.put("bye", "bye | Description: exits the program");
        cmdInfo.put("list", "list | Description: list all entered tasks");
        cmdInfo.put("done", "done <task index> | Description: marks by index a given task as done");
        cmdInfo.put("todo", "todo <name> | Description: adds a new todo task");
        cmdInfo.put("deadline", "deadline <name> /by <end date> | Description: adds a new deadline task");
        cmdInfo.put("event", "event <name> /at <start date - end date> | Description: adds a new event task");
        cmdInfo.put("help", "help | Description: list this help menu");
    }

    /**
     * Exits the program.
     */
    public void exit() {
        System.out.println("Bye! See you later :D");
    }

    /**
     * Parses, adds and prints the input from user as task.
     * @param input input provided by user
     */
    public void add(String input, ArrayList<Task> tasks) {
        //split input on first space to retrieve task type
        String[] parsedString = input.split("\\s+", 2);
        Task task;
        String taskType;
        String taskDetails;

        //attempt to create a task, inform user if input is invalid
        try {
            taskType = parsedString[0];
            if (!taskType.equals("todo") && !taskType.equals("deadline") && !taskType.equals("event")) {
                throw new DukeException("Invalid action, type 'help' for more options");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }

        String taskName;
        String[] parsedTaskDetails;

        try {
            taskDetails = parsedString[1];
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
                    throw new IndexOutOfBoundsException();
            }
            tasks.add(task);
            System.out.print("Got it! I have added this task:\n" + task + "\nYou have " + Task.numTasks + " task(s) now!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage for " + taskType + ": " + cmdInfo.get(taskType));
        }
    }

    /**
     * List all tasks entered by user.
     */
    public void list(ArrayList<Task> tasks) {
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + "." + task);
        }
    }

    /**
     * Checks and marks given task as done and informs user of success/failure.
     * @param input input provided by user
     */
    public void done(String input, ArrayList<Task> tasks) {
        String[] parsedString = input.split("\\s+");
        int taskId;

        try {
            taskId = Integer.parseInt(parsedString[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage for done: " + cmdInfo.get("done"));
            return;
        }

        try {
            Task task = tasks.get(taskId - 1);
            if (task.getStatus().equals("complete")) {
                System.out.println("Task is already completed!");
            } else {
                task.markCompleted();
                System.out.println("Yay your task is done! :D");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The specified task index does not exist!");
        }
    }

    /**
     * List all available command usage and descriptions.
     */
    public void help() {
        System.out.println("The available commands are as listed below:");
        for (String info : cmdInfo.values()) {
            System.out.println(info);
        }
    }
}
