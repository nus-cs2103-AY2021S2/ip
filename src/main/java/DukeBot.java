import java.util.ArrayList;
import java.util.List;

/**
 * Bot that handles user inputs, identifies specific commands and respond accordingly
 * Available commands: list, bye, done, delete, delete, event, deadline, todo
 */
public class DukeBot {
    private List<Task> taskList;
    private boolean isExit;
    private String commandOutput;

    /**
     * Constructor for DukeBot
     * Sets up duke bot to welcome user
     */
    public DukeBot() {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        commandOutput = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        respondToCommand(commandOutput);
    }

    /**
     * Executes processes depending on command given by user
     * @param text Text provided by user
     * @throws DukeException If task description is empty, task selection is invalid, task selection is empty
     */
    public void handleCommand(String text) throws DukeException {
        commandOutput = "";
        String[] commandLine = text.split(" ");
        String command = commandLine[0];
        String taskInfo = text.replaceFirst(command + " ", "");

        // Throws exception for ToDo, Event and Deadline tasks
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            if (taskInfo.equals(command)) {
                //Empty description
                throw new DukeException(command, DukeExceptionType.EMPTY_DESCRIPTION);
            }
        }

        // Throws exception for done and delete commands
        if (command.equals("done") || command.equals("delete")) {
            if (taskInfo.equals(command)) {
                throw new DukeException(command, DukeExceptionType.EMPTY_SELECTION);
            } else if (!isNumeric(taskInfo)) {
                // Selection not numeric
                throw new DukeException(command, DukeExceptionType.INVALID_INTEGER);
            } else if (Integer.parseInt(taskInfo) > taskList.size() || Integer.parseInt(taskInfo) <= 0) {
                // Selection out of taskList range
                throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
            }
        }

        // Sets up process to be done for specific commands
        switch (command) {
            case "list":
                listProcess();
                break;
            case "bye":
                byeProcess();
                break;
            case "done":
                doneProcess(taskInfo);
                break;
            case "delete":
                deleteProcess(taskInfo);
                break;
            case "event":
                eventProcess(taskInfo);
                break;
            case "deadline":
                deadlineProcess(taskInfo);
                break;
            case "todo":
                todoProcess(taskInfo);
                break;
            default:
                throw new DukeException(command, DukeExceptionType.UNKNOWN_INPUT);
        }

        respondToCommand(commandOutput);
    }

    /**
     * Sets up program to list out tasks
     */
    private void listProcess() {
        commandOutput = getTaskListContents();
    }

    /**
     * Sets up program for System exit
     */
    private void byeProcess() {
        isExit = true;
        commandOutput = "Bye. Hope to see you again soon!";
    }

    /**
     * Marks selected task as done inside list of tasks
     * @param selection Selected task
     */
    private void doneProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        task.markAsDone();
        commandOutput = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    /**
     * Deletes selected task from list of tasks
     * @param selection Selected task
     */
    private void deleteProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        taskList.remove(task);
        commandOutput = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    /**
     * Adds event task to list of tasks
     * @param taskInfo Task information containing task name, specific start and end time
     */
    private void eventProcess(String taskInfo) {
        String taskName = taskInfo.split(" /at")[0].replaceFirst("event ", "");
        String date = taskInfo.split(" /at ")[1];
        Task task = new Event(taskName, date);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    /**
     * Adds deadline task to list of tasks
     * @param taskInfo Task information containing task name, specific date/time to be done by
     */
    private void deadlineProcess(String taskInfo) {
        String taskName = taskInfo.split(" /by")[0].replaceFirst("deadline ", "");
        String date = taskInfo.split(" /by ")[1];
        Task task = new Deadline(taskName, date);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    /**
     * Adds ToDo task to list of tasks
     * @param taskName Task information containing only task name
     */
    private void todoProcess(String taskName) {
        Task task = new ToDo(taskName);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    /**
     * Iterates through the list of tasks and numbers each of them
     * @return Contents of list of tasks in String
     */
    private String getTaskListContents() {
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
    }

    /**
     * Shows how many tasks are remaining inside list of tasks
     * @return Size of list of tasks
     */
    private String getRemainingTasks() {
        return "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    /**
     * Echoes out a response to the user's input
     * @param selectedOutput Contains different output to be concatenated with main message depending on command
     */
    public void respondToCommand(String selectedOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + selectedOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

    /**
     * Used for DukeException handling, to check if user provides valid numeric selection when necessary
     * @param text String to check for numeric value
     * @return True if valid numeric, false if invalid
     */
    private boolean isNumeric(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

}
