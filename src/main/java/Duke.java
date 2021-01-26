import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.utils.Command;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class of the application.
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Creates a new instance of Duke.
     * @param filePath The save file path.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        ui = new Ui();
        loadData();
    }

    /**
     * Loads data from save file.
     */
    public void loadData() {
        try {
            ArrayList<Task> tasks = storage.load();
            taskList.setTaskList(tasks);
        } catch (IOException e) {
            ui.showErrorMessage("Sorry something when wrong loading your safe file :(");
            System.exit(0);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Saves data to save file.
     */
    public void saveData() {
        try {
            storage.save(taskList.getTaskList());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Processes input after it is parsed by the parser.
     * @param command Command that is to be excecuted.
     * @param tokens Input String split into tokens.
     * @throws DukeException
     */
    public void processInput(Command command, String[] tokens) throws DukeException {
        switch(command) {
            case SKIP:
                break;
            case BYE:
                ui.showGoodbyeMessage();
                System.exit(0);
                break;
            case DONE:
                try {
                    Task task = taskList.markAsDone(Integer.parseInt(tokens[1]) - 1);
                    ui.showSuccessfulDoneMessage(task);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
                break;
            case DELETE:
                try {
                    Task task = taskList.delete(Integer.parseInt(tokens[1]) - 1);
                    ui.showSuccessfulDeleteMessage(taskList.getSize(), task);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
                break;
            case LIST:
                ui.showTasks(taskList.getTaskList());
                break;
            default:
                try {
                    Task task = taskList.addTask(command, tokens[1].trim());
                    ui.showAddTaskMessage(taskList.getSize(), task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IncompleteInputException(command);
                }
        }

        saveData();
    }

    private void run() {
        ui.showWelcomeMessage();

        while (ui.hasMoreTokens()) {
            String input = ui.getUserCommand();

            try {
                String[] tokens = Parser.splitIntoSubstrings(input);
                Command command = Parser.parseCommand(tokens);
                processInput(command, tokens);
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
