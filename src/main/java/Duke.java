import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 * Driver class that takes in input and performs certain functions based on input.
 */
public class Duke {

    /**
     * Main class to take in user input.
     * @param params Standard user input params
     */
    public static void main(String[] params) throws IOException {
        Scanner sc = new Scanner(System.in);

        Ui getInput = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();

        // Starting string to prime the program
        String command = Ui.primer;

        // Creating directory if it does not exist
        storage.createDirectory();

        // Loading file from directory if it exists
        List<Task> tempList = storage.loadFile();
        TaskList taskList = new TaskList(tempList);

        while (parser.stillHaveCommands(command)) {
            if (command.equals("Hello")) {
                getInput.printStarter();
            } else if (command.startsWith("list")) {
                taskList.enumerateTasks();
            } else if (command.startsWith("done")) {
                String[] taskToDelete = command.split("\\s+");
                taskList.markAsDone(Integer.parseInt(taskToDelete[1]));
            } else if (command.startsWith("todo")) {
                try {
                    Todo currentTask = new Todo(command.substring(5));
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (StringIndexOutOfBoundsException indexError) {
                    getInput.invalidTodo();
                }
            } else if (command.startsWith("event")) {
                try {
                    String[] splitString = command.split("/at");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Event currentTask = new Event(eventDesc.substring(6), eventDate);
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    getInput.invalidEvent();
                }
            } else if (command.startsWith("deadline")) {
                try {
                    String[] splitString = command.split("/by");
                    String eventDesc = splitString[0];
                    String eventDate = splitString[1];
                    Deadline currentTask = new Deadline(eventDesc.substring(9), eventDate);
                    taskList.addToTasks(currentTask);
                    taskList.logTask(currentTask);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException indexError) {
                    getInput.invalidDeadline();
                }
            } else if (command.startsWith("delete")) {
                String[] splitString = command.split("\\s+");
                taskList.removeTask(Integer.parseInt(splitString[1]));
            } else if (command.startsWith("find")) {
                String keyword = command.split("\\s+")[1];
                taskList.retrieveByKeyword(keyword);
            } else {
                // Command is not recognized
                getInput.commandNotRecognized();
            }
            // Getting the next command from user
            command = sc.nextLine();
        }

        storage.saveFile(taskList.logAllTasks());

        getInput.printBye();

    }


}
