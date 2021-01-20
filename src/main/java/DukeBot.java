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

    /**
     * Constructor for DukeBot class
     * Configuration for DukeBot to welcome the user
     */
    public DukeBot() {
        output = BORDER + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + BORDER;
        System.out.println(output);
    }

    /**
     * Bot will carry out the different process depending on the user input
     * @param input provided by the user
     * @return boolean when the user enters bye command to terminate the DukeBot
     */
    public boolean echo(String input) {
        String[] commandStr = input.trim().split("\\s+");
        String taskAction = commandStr[0];
        boolean continueInput = true;

        switch (taskAction) {
            case "bye":
                output = BORDER + "\t" + " Bye. Hope to see you again soon!\n" + BORDER;
                continueInput = false;
                break;
            case "list":
                output = retrieveList();
                break;
            case "done":
                output = markDoneTask(Integer.parseInt(commandStr[1]));
                break;
            case "todo":
            case "deadline":
            case "event":
                this.numTasks++;
                output = handleNewTask(taskAction, commandStr);
                break;
            case "delete":
                this.numTasks--;
                output = handleDeleteTask(Integer.parseInt(commandStr[1]));
                break;
            default:
                output = handleInvalidInput();
                break;
        }
        System.out.println(output);
        return continueInput;
    }

    /**
     * Indicated a given task as done among the list of tasks
     * @param index provided when user enters an input like "done 2" to mark 2nd task on the list as done
     * @return the output string to be displayed in return of the user input
     */
    public String markDoneTask(int index) {
        if (index <= 0 || index > this.numTasks) {
            return handleInvalidValue();
        } else {
            Task doneTask = this.taskList.get(index - 1);
            doneTask.markAsDone();
            return BORDER + "\t" + " Nice! I've marked this task as done:\n" + "\t  " + doneTask.toString() + "\n"
                    + BORDER;
        }
    }

    /**
     * Iterating through the list of tasks and numbering the tasks
     * @return the list of tasks formatted in String
     */
    public String retrieveList() {
        StringBuilder currText = new StringBuilder(BORDER + "\t" + " Here are the tasks in your list:\n");

        for (int num = 1; num <= this.taskList.size(); num++) {
            Task currentTask = this.taskList.get(num - 1);
            currText.append("\t ").append(num).append(".").append(currentTask.toString()).append("\n");
        }
        currText.append(BORDER);
        return currText.toString();
    }

    /**
     * Manage the new task of todo, event or deadline types
     * @return the output string to be displayed in return of the user input
     */
    public String handleNewTask(String taskAction, String[] commandStr) {
        Task newTask;
        StringBuilder description = new StringBuilder();
        List<String> taskDetails = Arrays.asList(commandStr);
        String currText = BORDER + "\t" + " Got it. I've added this task: \n";

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
            return handleBlankDescription(taskAction);
        } else {
            this.taskList.add(newTask);
            return currText + "\t  " + newTask.toString() + "\n\t Now you have " + this.numTasks
                    + " tasks in the list.\n" + BORDER;
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
     * @return the output string to be displayed in return of the user input
     */
    public String handleDeleteTask(int index) {
        if (index <= 0 || index > this.numTasks) {
            return handleInvalidValue();
        } else {
            Task deleteTask = this.taskList.get(index - 1);
            this.taskList.remove(deleteTask);
            return BORDER + "\t" + "Noted. I've removed this task: \n" + "\t  " + deleteTask.toString()
                    + "\n\t Now you have " + this.numTasks + " tasks in the list.\n" + BORDER;
        }
    }

    /**
     * Manage the invalid input (Unrecognised commands) exception entered by the user
     * @return the output string to be displayed in return of the user input
     */
    public String handleInvalidInput() {
        DukeException exception = new DukeException(ExceptionType.INVALID_INPUT, "");
        return BORDER + "\t " + exception.getMessage() + "\n" + BORDER;
    }

    /**
     * Manage the invalid integer value (negative or out of list range) exception entered by the user
     * @return the output string to be displayed in return of the user input
     */
    public String handleInvalidValue() {
        DukeException exception = new DukeException(ExceptionType.INVALID_INTEGER, "");
        return BORDER + "\t " + exception.getMessage() + "\n" + BORDER;
    }

    /**
     * Manage the blank description exception entered by the user
     * @return the output string to be displayed in return of the user input
     */
    public String handleBlankDescription(String taskAction) {
        DukeException exception = new DukeException(ExceptionType.BLANK_DESCRIPTION, taskAction);
        return BORDER + "\t " + exception.getMessage() + "\n" + BORDER;
    }
}