import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


/**
 * Main implementation for the Duke chat-bot.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP v0.1
 */

public class Duke {
    /** Allows for easy change of the bot name in future. **/
    final private static String BOT_NAME = "DukeNukem";
    final private static String SEPARATORS = "~~~~~~~~~~~~~~~~~~~~~~";
    final private static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        initializeTasks();
        greetUser();
        listenInput();
        quit();
    }

    /**
     * Initializes the task list from the data file.
     */
    public static void initializeTasks() {
        Scanner scannerObject = null;
        List<String> fileContents;
        printMessage("Loading your past tasks...");
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            // mkdirs does not throw any exception even when failing
            dataDirectory.mkdirs();
        }
        File dataFile = new File(dataDirectory.getPath() + File.separator + "data.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            fileContents = Files.readAllLines(dataFile.toPath());
            for (int i = 0; i < fileContents.size(); i++) {
                String line = fileContents.get(i);
                if (line.length() < 1) {
                    continue;
                }
                String[] lineArray = line.split("\\|");
                boolean isDone = false;
                if (lineArray[1].strip().charAt(0) == '1') {
                    isDone = true;
                }
                if (lineArray[0].strip().charAt(0) == 'T') {
                    // todo
                    Todo newTodo = new Todo(lineArray[2].strip());
                    if (isDone) {
                        newTodo.setDone();
                    }
                    taskList.add(newTodo);
                } else if (lineArray[0].strip().charAt(0) == 'D') {
                    // deadline
                    Deadline newDeadline = new Deadline(lineArray[2].strip(), LocalDate.parse(lineArray[3].strip()));
                    if (isDone) {
                        newDeadline.setDone();
                    }
                    taskList.add(newDeadline);
                } else if (lineArray[0].strip().charAt(0) == 'E') {
                    // event
                    Event newEvent = new Event(lineArray[2].strip(), LocalDate.parse(lineArray[3].strip()));
                    if (isDone) {
                        newEvent.setDone();
                    }
                    taskList.add(newEvent);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        printMessage("Loading success!");
    }

    /**
     * Greets user with a message on the screen when the function is called.
     */
    public static void greetUser() {
        printMessage("Henlooooo~! My name is " + BOT_NAME);
        printMessage("What can I do for you today? :)");
    }

    /**
     * Prints separators providing for clarity of on-screen text.
     */
    public static void printSeparators() {
        System.out.println(SEPARATORS);
    }

    /**
     * Print the requested message in the bot's formatting.
     * @param message The message to be printed
     */
    public static void printMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints the task added and the total size of the list.
     * @param addedTask Task that was added to the list.
     */
    public static void addedTaskReply(Task addedTask) {
        printMessage("Wakarimashita! Task added to list:");
        printMessage(addedTask.toString());
        printMessage("The size of your task list is now: " + taskList.size());
    }

    /**
     * Mark the task passed to the method as complete.
     * @param inputString User input string.
     */
    public static void completeTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task doneTask = taskList.get(taskId).setDone();
            String typeOfTask = doneTask.getType();
            String completionOfTask = (doneTask.getDone() ? "1" : "0");
            String descriptionOfTask = doneTask.getDescription().strip();
            LocalDate date = LocalDate.now();
            if (doneTask instanceof Event) {
                date = ((Event) doneTask).getDate();
            } else if (doneTask instanceof Deadline) {
                date = ((Deadline) doneTask).getDate();
            }
            String oldString = typeOfTask + " | " + "0" + " | " + descriptionOfTask;
            String newString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            if (doneTask instanceof Event || doneTask instanceof Deadline) {
                oldString += " | " + date.toString();
                newString += " | " + date.toString();
            }
            deleteReplaceTaskFromDisk(oldString, newString);
            printMessage("Great~! Task completed:");
            printMessage(doneTask.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Delete the specified task from the list.
     * @param inputString User input string.
     */
    public static void deleteTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task deletedTask = taskList.remove(taskId);
            String typeOfTask = deletedTask.getType();
            String completionOfTask = (deletedTask.getDone() ? "1" : "0");
            String descriptionOfTask = deletedTask.getDescription().strip();
            String oldString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            deleteReplaceTaskFromDisk(oldString, "");
            printMessage("Okie! I've deleted the task from your list:");
            printMessage(deletedTask.toString());
            printMessage("The size of your task list is now: " + taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Listens to the user's input, and passes it to the input handler.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        while (stillListening) {
            printSeparators();
            String inputString = scannerObject.nextLine().trim();;
            printSeparators();
            try {
                handleInput(inputString);
            } catch (InvalidCommandException | InvalidInputException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled.
     */
    public static void handleInput(String inputString) {

        if (inputString.equals("list")) {
            printList();
        } else if (inputString.equals("bye")) {
            quit();
        } else if (inputString.startsWith("done")) {
            completeTask(inputString);
        } else if (inputString.startsWith("delete")) {
            deleteTask(inputString);
        } else if (inputString.startsWith("todo")) {
            try {
                String taskString = inputString.substring(5);
                Todo newTodo = new Todo(taskString);
                taskList.add(newTodo);
                String saveToDisk = "T | 0 | " + taskString;
                saveTaskToDisk(saveToDisk);
                addedTaskReply(newTodo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("event")) {
            try {
                String[] eventString = inputString.split("/at");
                String taskString = eventString[0].substring(6).trim();
                String dateTime = eventString[1].trim();
                LocalDate eventTime = LocalDate.parse(dateTime);
                Event newEvent = new Event(taskString, eventTime);
                taskList.add(newEvent);
                String saveToDisk = "E | 0 | " + taskString + " | " + eventTime;
                saveTaskToDisk(saveToDisk);
                addedTaskReply(newEvent);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                LocalDate deadlineTime = LocalDate.parse(eventString[1].trim());
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                taskList.add(newDeadline);
                String saveToDisk = "D | 0 | " + taskString + " | " + deadlineTime;
                saveTaskToDisk(saveToDisk);
                addedTaskReply(newDeadline);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Replaces a line in the file to facilitate deletion and replacement.
     * @param oldString String of the old task.
     * @param newString String of the new task.
     */
    public static void deleteReplaceTaskFromDisk(String oldString, String newString) {
        File dataDirectory = new File("data");
        File dataFile = new File(dataDirectory.getPath() + File.separator + "data.txt");
        try {
            List<String> fileContents = new ArrayList<>(Files.readAllLines(dataFile.toPath()));
            for (int i = 0; i < fileContents.size(); i++) {
                if (fileContents.get(i).equals(oldString)) {
                    fileContents.set(i, newString);
                    break;
                }
            }
            Files.write(dataFile.toPath(), fileContents);
        } catch (IOException e) {

        }
    }

    public static void saveTaskToDisk(String string) {
        File dataDirectory = new File("data");
        File dataFile = new File(dataDirectory.getPath() + File.separator + "data.txt");
        try {
            FileWriter writer = new FileWriter(dataFile, true);
            writer.write(string + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the list of tasks in the list, including the status.
     */
    public static void printList() {
        if (taskList.size() < 1) {
            printMessage("There are no tasks in your list! :c");
            return;
        }
        printMessage("Tasks in your list are~: ");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            printMessage((i + 1) + "." + task);
        }
    }

    /**
     * Quits the program and provides provisions for clean-up.
     */
    public static void quit() {
        printMessage("Hope you had an enjoyable experience! Good-bye~");
        System.exit(0);
    }
}
