//import java.lang.reflect.Array;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) {
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

    private TaskResult executeCommand(TaskManager taskManager) throws DukeException {
            TaskAction action = taskManager.execute();
            TaskResult result = tasks.executeOperation(action);
            return result;
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    public String getResponse(String userInput) throws DukeException {
        if (userInput.equals("bye")){
            exit();
            return ui.showGoodbyeMessage();
        }
        try {
            Parser commandParser = new Parser();
            TaskManager taskManager = commandParser.parseCommand(userInput);
            TaskResult result = executeCommand(taskManager);
            storage.updateTaskList(tasks);
            String response = ui.showResultToUser(tasks, result);
            return response;
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }
}