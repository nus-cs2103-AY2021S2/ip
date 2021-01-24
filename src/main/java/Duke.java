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
    final protected static String BOT_NAME = "DukeNukem";
    private static Ui ui;
    private static TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            tasks = new TaskList();
        } catch (DukeException e) {
            Ui.printMessage(e.getMessage());
        }
    }

    public void run() {
        listenInput();
        quit();
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }

    /**
     * Listens to the user's input, and passes it to the input handler.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        while (stillListening) {
            Ui.printSeparators();
            String inputString = scannerObject.nextLine().trim();;
            Ui.printSeparators();
            try {
                handleInput(inputString);
            } catch (InvalidCommandException | InvalidInputException | InvalidTaskException e) {
                Ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled.
     */
    public static void handleInput(String inputString) {

        if (inputString.equals("list")) {
            tasks.print();
        } else if (inputString.equals("bye")) {
            quit();
        } else if (inputString.startsWith("done")) {
            tasks.completeTask(inputString);
        } else if (inputString.startsWith("delete")) {
            tasks.deleteTask(inputString);
        } else if (inputString.startsWith("todo")) {
            try {
                String taskString = inputString.substring(5);
                Todo newTodo = new Todo(taskString);
                tasks.addTask(newTodo);
                String saveToDisk = "T | 0 | " + taskString;
                saveTaskToDisk(saveToDisk);
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
                tasks.addTask(newEvent);
                String saveToDisk = "E | 0 | " + taskString + " | " + eventTime;
                saveTaskToDisk(saveToDisk);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                LocalDate deadlineTime = LocalDate.parse(eventString[1].trim());
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                tasks.addTask(newDeadline);
                String saveToDisk = "D | 0 | " + taskString + " | " + deadlineTime;
                saveTaskToDisk(saveToDisk);
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
            Ui.printMessage(e.getMessage());
        }
    }

    /**
     * Quits the program and provides provisions for clean-up.
     */
    public static void quit() {
        Ui.printMessage("Hope you had an enjoyable experience! Good-bye~");
        System.exit(0);
    }
}
