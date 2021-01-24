import java.io.IOException;

public class Main {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public static void main(String[] args) {
        new Main().run(args);
    }

    public void run(String[] args) {
        // Initialize the required components
        initialize(args);

        // Run infinite loop asking for user command until user enter exit command
        runLoop();

        // Exit the program
        exit();
    }

    private void initialize(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initializeStorage(args);
            this.taskList = storage.loadTasks();

            // Print greeting
            ui.printGreeting();
        } catch (InvalidStorageFilePathException ex) {
            ui.print("Failed to initialize storage. Exiting...");
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            ui.print("Failed to load storage file. Exiting...");
            throw new RuntimeException(ex);
        }
    }

    private void exit() {
        // Print exit message
        ui.printExitMessage();
    }

    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        if (args.length > 0) {
            // User has specified a file path for the storage
            return new Storage(args[0]);
        } else {
            // Using the default file path as user did not specify a file path
            return new Storage();
        }
    }

    private void runLoop() {
        Command command = null;
        do {
            try {
                String userInput = ui.getUserInput();
                command = new Parser().parseCommand(userInput);
                CommandResult commandResult = executeCommand(command);
                storage.saveTasksIfPresent(commandResult.getUpdatedTaskList());
                updateTaskListIfPresent(commandResult.getUpdatedTaskList());
                ui.print(commandResult.getMessageForUser());
            } catch (InvalidCommandException | StorageException | InvalidDescriptionException |
                    NoDescriptionException ex) {
                ui.print(ex.getMessage());
            }
        } while (!ByeCommand.isByeCommand(command));
    }
    
    private CommandResult executeCommand(Command command) {
        command.setTaskList(taskList);
        return command.execute();
    }
    
    private void updateTaskListIfPresent(TaskList taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }

}
