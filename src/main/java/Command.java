import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Command class handles the logic of all allowed user commands for Duke.
 */
public class Command {

    //tracks all valid commands
    private enum Cmd {
        BYE,
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        HELP
    }

    //list storing commands/descriptions, ideally store in json file
    private final static HashMap<String, String> cmdInfo = new HashMap<>();

    /**
     * Constructor for Command class that initialises all valid commands.
     */
    public Command() {
        cmdInfo.put(Cmd.BYE.toString(), "bye | Description: exits the program");
        cmdInfo.put(Cmd.LIST.toString(), "list | Description: list all entered tasks");
        cmdInfo.put(Cmd.DONE.toString(), "done <task index> | Description: marks by index a given task as done");
        cmdInfo.put(Cmd.TODO.toString(), "todo <name> | Description: adds a new todo task");
        cmdInfo.put(Cmd.DEADLINE.toString(), "deadline <name> /by <end date> | Description: adds a new deadline task");
        cmdInfo.put(Cmd.EVENT.toString(), "event <name> /at <start date - end date> | Description: adds a new event task");
        cmdInfo.put(Cmd.DELETE.toString(), "delete <task index> | Description: delete by index a given task");
        cmdInfo.put(Cmd.HELP.toString(), "help | Description: list this help menu");
    }

    /**
     * Parses input from user to determine action to take.
     * @param input input provided by user
     * @param tasks list of tasks entered by user
     */
    public void parseInput(String input, ArrayList<Task> tasks) {
        //program exits on bye
        if (input.toUpperCase().equals(Cmd.BYE.toString())) {
            exit();
            //program shows entered tasks on list
        } else if (input.toUpperCase().equals(Cmd.LIST.toString())) {
            list(tasks);
            //program marks task as complete on done
        } else if (input.toUpperCase().startsWith(Cmd.DONE.toString())) {
            done(input, tasks);
            //program removes task on delete
        } else if (input.toUpperCase().startsWith(Cmd.DELETE.toString())) {
            delete(input, tasks);
            //program list help commands
        } else if (input.toUpperCase().equals(Cmd.HELP.toString())) {
            help();
            //program tries to add task otherwise
        } else {
            add(input, tasks);
        }
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
      * @param tasks list of tasks entered by user
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
            if (!taskType.toUpperCase().equals(Cmd.TODO.toString())
                && !taskType.toUpperCase().equals(Cmd.DEADLINE.toString())
                && !taskType.toUpperCase().equals(Cmd.EVENT.toString()))
            {
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
            switch (Cmd.valueOf(taskType.toUpperCase())) {
                case TODO:
                    taskName = taskDetails.trim();
                    task = new ToDo(Task.numTasks + 1, taskName, "incomplete");
                    DataHandler.saveData(String.valueOf(task.getId()), task.getTaskName(), task.getStatus(), task.getType(), "");
                    break;
                case DEADLINE:
                    parsedTaskDetails = taskDetails.split("/by");
                    taskName = parsedTaskDetails[0].trim();
                    String endDate = parsedTaskDetails[1].trim();
                    task = new Deadline(Task.numTasks + 1, taskName, "incomplete", endDate);
                    DataHandler.saveData(String.valueOf(task.getId()), task.getTaskName(), task.getStatus(), task.getType(), endDate);
                    break;
                case EVENT:
                    parsedTaskDetails = taskDetails.split("/at");
                    taskName = parsedTaskDetails[0].trim();
                    String startEndDate = parsedTaskDetails[1].trim();
                    task = new Event(Task.numTasks + 1, taskName, "incomplete", startEndDate);
                    DataHandler.saveData(String.valueOf(task.getId()), task.getTaskName(), task.getStatus(), task.getType(), startEndDate);
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }
            tasks.add(task);
            System.out.print("Got it! I have added this task:\n" + task + "\nYou have " + Task.numTasks + " task(s) now!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage for " + taskType + ": " + cmdInfo.get(taskType.toUpperCase()));
        }
    }

    /**
     * List all tasks entered by user.
     * @param tasks list of tasks entered by user
     */
    public void list(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no task at the moment!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                System.out.println(i + "." + task);
            }
        }
    }

    /**
     * Checks and marks given task as done and informs user of success/failure.
     * @param input input provided by user
     * @param tasks list of tasks entered by user
     */
    public void done(String input, ArrayList<Task> tasks) {
        String[] parsedString = input.split("\\s+");
        int taskId;

        try {
            taskId = Integer.parseInt(parsedString[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage for done: " + cmdInfo.get(Cmd.DONE.toString()));
            return;
        } catch (NumberFormatException e) {
            System.out.println("Specify a valid task number to mark as complete!");
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
     * Deletes given task as done and informs user of success/failure.
     * @param input input provided by user
     * @param tasks list of tasks entered by user
     */
    public void delete(String input, ArrayList<Task> tasks) {
        String[] parsedString = input.split("\\s+");
        int taskId;

        try {
            taskId = Integer.parseInt(parsedString[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Usage for delete: " + cmdInfo.get(Cmd.DELETE.toString()));
            return;
        } catch (NumberFormatException e) {
            System.out.println("Specify a valid task number to delete!");
            return;
        }

        try {
            Task task = tasks.get(taskId - 1);
            tasks.remove(task);
            System.out.println("The following task has been deleted:\n" + task);
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
