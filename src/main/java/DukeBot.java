import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

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
    public DukeBot() throws IOException {
        taskList = new ArrayList<>();
        taskList.add(null);
        isExit = false;
        commandOutput = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        respondToCommand(commandOutput);
        loadData();
    }

    /**
     * Executes processes depending on command given by user
     *
     * @param text Text provided by user
     * @throws DukeException If task description is empty, task selection is invalid, task selection is empty
     */
    public void handleCommand(String text) throws DukeException, IOException {
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
            } else if (!Utility.isNumeric(taskInfo)) {
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
            saveData();
            break;
        case "delete":
            deleteProcess(taskInfo);
            saveData();
            break;
        case "event":
            eventProcess(taskInfo);
            saveData();
            break;
        case "deadline":
            deadlineProcess(taskInfo);
            saveData();
            break;
        case "todo":
            todoProcess(taskInfo);
            saveData();
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
    private void eventProcess(String taskInfo) throws DukeException {
        String taskName = taskInfo.split(" /at")[0].replaceFirst("event ", "");
        String date = taskInfo.split(" /at ")[1];
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(taskName, LocalDate.parse(date));
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    /**
     * Adds deadline task to list of tasks
     * @param taskInfo Task information containing task name, specific date/time to be done by
     */
    private void deadlineProcess(String taskInfo) throws DukeException {
        String taskName = taskInfo.split(" /by")[0].replaceFirst("deadline ", "");
        String date = taskInfo.split(" /by ")[1];
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(taskName, LocalDate.parse(date));
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void saveData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/data/duke.txt";
        String dirPath = System.getProperty("user.dir") + "/data";
        File file = new File(filePath);
        File dir = new File(dirPath);

        if (!Files.isDirectory(Paths.get(dirPath))) {
            // Create data folder and duke.txt if do not exist
            dir.mkdir();
            file.createNewFile();
        } else if (!file.exists()) {
            // Create duke.txt if do not exist
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        for (int i = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            fileWriter.write(task.writeContentFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    private void loadData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/data/duke.txt";
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] taskInfo = sc.nextLine().split("[ | ]+");
            Task task = new Task("");
            switch(taskInfo.length) {
            case 3:
                // ToDo task
                task = new ToDo(taskInfo[2]);
                break;
            case 4:
                if (taskInfo[0].equals('D')) {
                    // Deadline task
                    task = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[3]));
                } else {
                    //Event task
                    task = new Event(taskInfo[2], LocalDate.parse(taskInfo[3]));
                }
                break;
            default:
                break;
            }

            // Previously marked as done
            if (Integer.parseInt(taskInfo[1]) == 1) {
                task.markAsDone();
            }
            taskList.add(task);
        }
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
}
