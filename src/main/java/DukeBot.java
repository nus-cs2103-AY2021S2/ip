import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * DukeBot that manages user input, recognise and response to inputs accordingly
 * Commands that are available: list, bye, done, delete, todo, delete, event, deadline
 */
@SuppressWarnings("ALL")
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
    public DukeBot() throws IOException {
        this.continueInput = true;
        this.output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        outputMessage(this.output);
        loadData();
    }

    public void saveData() throws IOException {
        String pathDirectory = System.getProperty("user.dir") + "/data";
        String fileDirectory = pathDirectory + "/Duke.txt";
        File dataDirectory = new File(pathDirectory);
        File dataFile = new File(fileDirectory);

        if(!(Files.isDirectory(Paths.get(pathDirectory)))) {
            // Handles folder does not exist case
            dataDirectory.mkdir();
        } if(!dataFile.exists()) {
            // Handles file does not exist
            dataFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(dataFile, false);
        for (Task task : taskList) {
            fileWriter.write(task.formatTask() + System.lineSeparator());
        }
        fileWriter.close();

    }

    public void loadData() throws IOException {
        String fileDirectory = System.getProperty("user.dir");
        System.out.println(fileDirectory);
        File dataFile = new File(fileDirectory + "/data/Duke.txt");
        Scanner sc = new Scanner(dataFile);
        Task newTask = new Task("");

        while (sc.hasNext()) {
            String[] taskDetails = sc.nextLine().split("[|]");
            String taskType = taskDetails[0];

            switch (taskType) {
            case "T":
                newTask = new ToDo(taskDetails[2]);
                break;
            case "E":
                newTask = new Event(taskDetails[2], taskDetails[3]);
                break;
            case "D":
                newTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            default:
                break;
            }

            if(taskDetails[1].equals("1")) {
                newTask.markAsDone();
            }
            this.taskList.add(newTask);
        }
    }

    /**
     * Bot will carry out the different process depending on the user input
     * @param input provided by the user
     * @throws DukeException if the user enters an invalid input
     */
    public void echo(String input) throws DukeException, IOException {
        String[] commandStr = input.trim().split("\\s+");
        String taskAction = commandStr[0];

        switch (taskAction) {
        case "bye":
            this.output = " Bye. Hope to see you again soon!";
            this.continueInput = false;
            break;
        case "list":
            retrieveList();
            break;
        case "done":
            markDoneTask(Integer.parseInt(commandStr[1]));
            saveData();
            break;
        case "todo":
        case "deadline":
        case "event":
            handleNewTask(taskAction, input.replaceFirst(taskAction, ""));
            saveData();
            break;
        case "delete":
            handleDeleteTask(Integer.parseInt(commandStr[1]));
            saveData();
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
     * Output will be the list of tasks formatted in String
     */
    public void retrieveList() {
        StringBuilder currText = new StringBuilder(" Here are the tasks in your list:");

        for (int num = 1; num <= this.taskList.size(); num++) {
            Task currentTask = this.taskList.get(num - 1);
            currText.append("\n\t ").append(num).append(".").append(currentTask.toString());
        }
        this.output = currText.toString();
    }

    /**
     * Manage the new task of todo, event or deadline types
     * @throws DukeException if the task description is empty
     */
    public void handleNewTask(String taskAction, String taskDescription) throws DukeException {
        Task newTask;
        this.output = " Got it. I've added this task: \n";

        if (!taskAction.equals("todo")) {
            String[] result;

            if (taskAction.equals("event")) {
                result = taskDescription.split("/at");
                newTask = new Event(result[0], result[1]);
            } else {
                result = taskDescription.split("/by");
                newTask = new Deadline(result[0], result[1]);
            }
        } else {
            newTask = new ToDo(taskDescription);
        }

        if (newTask.getDescription().equals("")) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, taskAction);
        } else {
            this.taskList.add(newTask);
            this.numTasks++;
            this.output += "\t  " + newTask.toString() + "\n\t Now you have "
                    + this.numTasks + " tasks in the list.";
        }
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
            this.numTasks--;
            this.output = "Noted. I've removed this task: \n" + "\t  " + deleteTask.toString()
                    + "\n\t Now you have " + this.numTasks + " tasks in the list.";
        }
    }
}