package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.InvalidCommandException;
import duke.commands.InvalidDescriptionException;
import duke.commands.NoDescriptionException;

import duke.parser.Parser;

import duke.storage.InvalidStorageFilePathException;
import duke.storage.Storage;
import duke.storage.StorageException;

import duke.tasks.TaskList;

import duke.ui.Ui;

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
            // Initialize the required components
            ui = new Ui();
            storage = initializeStorage(args);
            taskList = storage.loadTasks();

            // Print the welcome greeting 
            ui.printDivider();
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
        ui.printDivider();
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
                // Ask for user input
                ui.printDivider();
                String userInput = ui.getUserInput();
                ui.printDivider();

                // Parse the user input into an executable command
                command = new Parser().parseCommand(userInput);

                // Execute the command
                CommandResult commandResult = executeCommand(command);

                // Update the cached task list and save it to file
                storage.saveTasksIfPresent(commandResult.getUpdatedTaskList());
                updateTaskListIfPresent(commandResult.getUpdatedTaskList());

                // Print the message for the user
                ui.print(commandResult.getMessageForUser());
            } catch (InvalidCommandException | StorageException | InvalidDescriptionException
                    | NoDescriptionException ex) {
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
