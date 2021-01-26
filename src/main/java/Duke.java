import duke.exceptions.DukeException;
import duke.exceptions.IncompleteInputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.utils.Command;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        ui = new Ui();
        loadData();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

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

    public void saveData() {
        try {
            storage.save(taskList.getTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            System.exit(0);
        }
    }

    public void processInput(Command command, String[] tokens) throws DukeException {
        switch (command) {
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
            ui.showTasks(taskList.getTasks());
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

    public void run() {
        ui.showWelcomeMessage();

        while (ui.hasMoreTokens()) {
            String input = ui.getUserCommand();

            try {
                String[] tokens = Parser.splitIntoTokens(input);
                Command command = Parser.parseCommand(tokens);
                processInput(command, tokens);
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
