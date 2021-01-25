import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DukeBot that manages user input, recognise and response to inputs accordingly
 * Commands that are available: list, bye, done, delete, todo, delete, event, deadline
 */
public class DukeBot {
    private static final String BORDER = "\t___________________________________\n";
    private ArrayList<Task> taskList;
    private int numTasks;
    private String output;
    private boolean continueInput;

    /**
     * Constructor for DukeBot class
     * Configuration for DukeBot to welcome the user
     */
    public DukeBot() throws IOException {
        this.numTasks = 0;
        this.taskList = new ArrayList<>();
        this.continueInput = true;
        this.output = " Hello! I'm Duke\n" + "\t What can I do for you?";
        outputMessage(this.output);
        loadData();
    }

    /**
     * Bot will check whether there is a folder named data and a file Duke.txt exist in project directory
     * @return File called Duke.txt inside project root/data directory
     * @throws IOException if the data folder or file Duke.txt does not exist
     */
    public File fileConfiguration() throws IOException {
        String pathDirectory = System.getProperty("user.dir") + "/data";
        String fileDirectory = pathDirectory + "/Duke.txt";
        File dataDirectory = new File(pathDirectory);
        File dataFile = new File(fileDirectory);

        if(!(Files.isDirectory(Paths.get(pathDirectory)))) {
            // Handles folder does not exist case
            dataDirectory.mkdir();
            dataFile.createNewFile();
        } else if(!dataFile.exists()) {
            // Handles file does not exist
            dataFile.createNewFile();
        }
        return dataFile;
    }

    /**
     * Save data into directory whenever user perform certain tasks like delete, done or there is a new task created
     */
    public void saveData() throws IOException {
        File dataFile = fileConfiguration();
        FileWriter fileWriter = new FileWriter(dataFile, false);

        for (Task task : taskList) {
            fileWriter.write(task.formatTask() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Load data from directory whenever user request the bot to list the tasks
     */
    public void loadData() throws IOException {
        this.taskList = new ArrayList<>();
        File dataFile = fileConfiguration();
        Scanner sc = new Scanner(dataFile);

        while (sc.hasNext()) {
            String[] taskDetails = sc.nextLine().split("[|]");
            String taskType = taskDetails[0];
            Task newTask = null;

            switch (taskType) {
            case "T":
                newTask = new ToDo(taskDetails[2]);
                break;
            case "E":
                newTask = new Event(taskDetails[2], LocalDate.parse(taskDetails[3]));
                break;
            case "D":
                newTask = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                break;
            default:
                break;
            }
            if (taskType.equals("T") || taskType.equals("E") || taskType.equals("D")) {
                if (taskDetails[1].equals("1")) {
                    newTask.markAsDone();
                }
                this.taskList.add(newTask);
            }
        }
    }

    /**
     * Bot will carry out the different process depending on the user input
     * @param input provided by the user
     * @throws DukeException if the user enters an invalid input
     * @throws IOException if the data folder or file Duke.txt does not exist
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
     * @param taskAction type of task (todo, event or deadline)
     * @param taskDescription details of the task
     * @throws DukeException if the task description is empty
     */
    public void handleNewTask(String taskAction, String taskDescription) throws DukeException {
        Task newTask;
        String[] description;
        this.output = " Got it. I've added this task: \n";

        if (taskDescription.equals("")) {
            throw new DukeException(ExceptionType.BLANK_DESCRIPTION, taskAction);
        }
        switch (taskAction) {
        case "event":
            description = handleEventDeadline(taskAction, taskDescription);
            newTask = new Event(description[0], LocalDate.parse(description[1],
                    DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        case "deadline":
            description = handleEventDeadline(taskAction, taskDescription);
            newTask = new Deadline(description[0], LocalDate.parse(description[1],
                    DateTimeFormatter.ofPattern("MMM dd yyyy")));
            break;
        default:
            newTask = new ToDo(taskDescription);
            break;
        }
        this.taskList.add(newTask);
        this.numTasks++;
        this.output += "\t  " + newTask.toString() + "\n\t Now you have " + this.numTasks + " tasks in the list.";
    }

    public String[] handleEventDeadline(String taskAction, String taskDescription) throws DukeException {
        String[] result;
        String dateTime;

        if (taskAction.equals("event")) {
            result = taskDescription.trim().split(" /at ");
        } else {
            result = taskDescription.trim().split(" /by ");
        }

        dateTime = result[1];

        if (!validDateTime(dateTime)) {
            throw new DukeException(ExceptionType.INVALID_DATETIME, "");
        }
        return result;
    }

    /**
     * Delete the task as entered by the user input by removing the task from the list of tasks
     * @param index provided when user enters an input like "delete 2" to delete 2nd task on the list
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

    public boolean validDateTime(String dateTime) {
        try
        {
            LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        catch (DateTimeParseException ex)
        {
            return false;
        }
        return true;
    }
}
