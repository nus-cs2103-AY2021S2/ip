import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DukeBot that manages user input, recognise and response to inputs accordingly
 * Commands that are available: list, bye, done, delete, todo, delete, event, deadline
 */
public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private final ArrayList<Task> taskList = new ArrayList<>();
    private int numTasks;
    private String output;
    private boolean continueInput;

    /**
     * Constructor for DukeBot class
     * Configuration for DukeBot to welcome the user
     */
    public DukeBot() {
        this.continueInput = true;
        this.output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        outputMessage(this.output);
    }

    /**
     * Bot will carry out the different process depending on the user input
     * @param input provided by the user
     * @throws DukeException if the user enters an invalid input
     */
    public void echo(String input) throws DukeException {
        String[] commandStr = input.trim().split("\\s+");
        String taskAction = commandStr[0];

        switch (taskAction) {
            case "bye":
                this.output = " Bye. Hope to see you again soon!";
                this.continueInput = false;
                break;
            case "list":
                this.output = retrieveList();
                break;
            case "done":
                markDoneTask(Integer.parseInt(commandStr[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                this.numTasks++;
                handleNewTask(taskAction, commandStr);
                break;
            case "delete":
                this.numTasks--;
                handleDeleteTask(Integer.parseInt(commandStr[1]));
                break;
            default:
                throw new DukeException(ExceptionType.INVALID_INPUT, "");
        }
        outputMessage(this.output);
    }

    /**
     * Return a response from the input of user
     * @param message consists of the return output to be displayed to the user
     */
    public void outputMessage(String message) {
        System.out.println(BORDER + "\t" + message + "\n" + BORDER);

        if(!this.continueInput) {
            System.exit(0);
        }
    }

    /**
     * Indicated a given task as done among the list of tasks
     * @param index provided when user enters an input like "done 2" to mark 2nd task on the list as done
     * @throws DukeException if the integer value entered by user is negative, 0 or out of list range
     */
    public void markDoneTask(int index) throws DukeException{
        if (index <= 0 || index > this.numTasks) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        } else {
            Task doneTask = this.taskList.get(index - 1);
            doneTask.markAsDone();
            this.output = " Nice! I've marked this task as done:\n" + "\t  " + doneTask.toString();
        }
    }

    /**
     * Iterating through the list of tasks and numbering the tasks
     * @return the list of tasks formatted in String
     */
    public String retrieveList() {
        StringBuilder currText = new StringBuilder(" Here are the tasks in your list:");

        for (int num = 1; num <= this.taskList.size(); num++) {
            Task currentTask = this.taskList.get(num - 1);
            currText.append("\n\t ").append(num).append(".").append(currentTask.toString());
        }
        return currText.toString();
    }

    /**
     * Manage the new task of todo, event or deadline types
     * @throws DukeException if the task description is empty
     */
    public void handleNewTask(String taskAction, String[] commandStr) throws DukeException {
        Task newTask;
        StringBuilder description = new StringBuilder();
        List<String> taskDetails = Arrays.asList(commandStr);
        this.output = " Got it. I've added this task: \n";

        if (!taskAction.equals("todo")) {
            String[] result = handleEventDeadLine(taskDetails);

            if (taskAction.equals("event")) {
                newTask = new Event(result[0], result[1]);
            } else {
                newTask = new Deadline(result[0], result[1]);
            }
        } else {
            for (int num = 1; num < taskDetails.size(); num++) {
                String curr = taskDetails.get(num);
                description.append(curr).append(" ");
            }
            newTask = new ToDo(description.toString());
        }

        if (newTask.getDescription().equals("")) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, taskAction);
        } else {
            this.taskList.add(newTask);
            this.output += "\t  " + newTask.toString() + "\n\t Now you have "
                    + this.numTasks + " tasks in the list.";
        }
    }

    /**
     * Sub-function to manage the new task of event or deadline types only
     * @return the output string array of description and date/time as required
     */
    public String[] handleEventDeadLine(List<String> taskDetails) {
        int num;
        StringBuilder description = new StringBuilder();
        StringBuilder dateTime = new StringBuilder();
        String[] result = new String[2];

        for (num = 1; num < taskDetails.size(); num++) {
            String curr = taskDetails.get(num);
            if (curr.contains("/")) {
                break;
            }
            description.append(curr).append(" ");
        }
        for (int i = num + 1; i < taskDetails.size(); i++) {
            dateTime.append(taskDetails.get(i));
            if (i < taskDetails.size() - 1) {
                dateTime.append(" ");
            }
        }
        result[0] = description.toString();
        result[1] = dateTime.toString();
        return result;
    }

    /**
     * Delete the task as entered by the user input by removing the task from the list of tasks
     * @throws DukeException if the integer value entered by user is negative, 0 or out of list range
     */
    public void handleDeleteTask(int index) throws DukeException {
        if (index <= 0 || index > this.numTasks) {
            throw new DukeException(ExceptionType.INVALID_INTEGER, "");
        } else {
            Task deleteTask = this.taskList.get(index - 1);
            this.taskList.remove(deleteTask);
            this.output = "Noted. I've removed this task: \n" + "\t  " + deleteTask.toString()
                    + "\n\t Now you have " + this.numTasks + " tasks in the list.";
        }
    }
}