//import java.lang.reflect.Array;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) throws DukeException {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] launchArgs) throws Exception {
        new Duke(System.getProperty("user.dir") + "/data/tasks.txt").run();
    }

    private void run() throws DukeException {
        runCommandLoopUntilExit();
        exit();
    }

    private void runCommandLoopUntilExit() {
        String commandText = "";
        do {
            // take user input
            commandText = ui.getUserCommand();

            // identify operator and execute command accordingly
            if (commandText.equals("bye")){
                break;
            }
            try {
                Parser commandParser = new Parser();
                TaskManager taskManager = commandParser.parseCommand(commandText);
                TaskResult result = executeCommand(taskManager);
                storage.updateTaskList(tasks);
                ui.showResultToUser(tasks, result);

            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        } while (!commandText.equals("bye"));
    }


    private TaskResult executeCommand(TaskManager taskManager) throws DukeException {
            TaskAction action = taskManager.execute();
            TaskResult result = tasks.executeOperation(action);
            return result;
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}