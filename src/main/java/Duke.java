import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Driver class that takes in input and performs certain functions based on input.
 */
public class Duke {

    /**
     * Helper method that processes the user input and returns corresponding for the
     * user. GUI version.
     * @param command User input, e.g. list, deadline...
     * @param taskListStored Retrieved task list from local disk
     * @return String to be displayed to user in dialogue box
     * @throws IOException
     */
    public String getOutput(String command, List<Task> taskListStored) throws IOException {
        Parser parser = new Parser();
        Ui getInput = new Ui();
        TaskList taskList = new TaskList(taskListStored);
        Storage storage = new Storage();
        storage.createDirectory();

        String output = "";

        if (parser.isStart(command)) {
            output = getInput.printStart();
        } else if (parser.isList(command)) {
            output = taskList.enumerateTasks();
        } else if (parser.isDone(command)) {
            String[] taskToDelete = command.split("\\s+");
            output = taskList.markAsDone(Integer.parseInt(taskToDelete[1]));
        } else if (parser.isTodo(command)) {
            try {
                Todo currentTask = new Todo(command.substring(5));
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidTodo();
            }
        } else if (parser.isEvent(command)) {
            try {
                String[] splitString = command.split("/at");
                Event currentTask = new Event(splitString);
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidEvent();
            }
        } else if (parser.isDeadline(command)) {
            try {
                String[] splitString = command.split("/by");
                Deadline currentTask = new Deadline(splitString);
                taskList.addToTasks(currentTask);
                output = taskList.logTask(currentTask);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidDeadline();
            }
        } else if (parser.isDelete(command)) {
            try {
                String[] splitString = command.split("\\s+");
                output = taskList.removeTask(Integer.parseInt(splitString[1]));
            } catch (ArrayIndexOutOfBoundsException indexError) {
                output = getInput.outInvalidDelete();
            }
        } else if (parser.isFind(command)) {
            try {
                output = taskList.retrieveByKeyword(command);
            } catch (ArrayIndexOutOfBoundsException indexError) {
                output  = getInput.outInvalidFind();
            }
        } else if (parser.isBye(command)) {
            output = getInput.outBye();
        } else if (parser.isHelp(command)) {
            output = getInput.outHelp();
        } else {
            // Command is not recognized
            output = getInput.outNotRecognized();
        }
        // Saving updated tasks to local disk
        storage.saveFile(taskList.logAllTasks());

        return output;
    }


    /**
     * Main class to take in user input. CLI version.
     * @param params Standard user input params
     */
    public static void main(String[] params) throws IOException {
        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage();
        // Starting string to prime the program
        String command = Ui.primer;

        // Creating directory if it does not exist
        storage.createDirectory();

        // Loading file from directory if it exists
        List<Task> tempList = storage.loadFile();

        Duke duke = new Duke();

        while (!command.startsWith("bye")) {
            System.out.println(duke.getOutput(command, tempList));
            command = sc.nextLine();
        }
    }


}
