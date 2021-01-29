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

/**
 * The main entry point to the chatbot application.
 * Initializes the application and starts user interaction.
 */
public class Main {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Entry point of the application.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the application until user terminates with an exit command.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     */
    public void run(String[] args) {
        // Initialize the required components
        initialize(args);

        // Run infinite loop asking for user command until user enter exit command
        runLoop();

        // Exit the program
        exit();
    }

    /**
     * Initializes the required components and prints the welcome greeting.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     */
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
            ui.print(ex.getMessage());
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            ui.print("Failed to load tasks from storage file. Exiting...");
            throw new RuntimeException(ex);
        }
    }

    /**
     * Prints the exit message
     */
    private void exit() {
        ui.printExitMessage();
        ui.printDivider();
    }

    /**
     * Initializes the storage using the specified filepath. If user did not specify a filepath,
     * then the default filepath will be used.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     * @return a Storage object that is used to read and write to a file
     * @throws InvalidStorageFilePathException if the specified filepath is invalid
     */
    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        if (args.length > 0) {
            // User has specified a file path for the storage
            return new Storage(args[0]);
        } else {
            // Using the default file path as user did not specify a file path
            return new Storage();
        }
    }

    /**
     * Runs the loop asking for user commands and execute the command until user enters exit command.
     */
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

    /**
     * Executes the command and return a CommandResult instance.
     *
     * @param command user command
     * @return result command
     */
    private CommandResult executeCommand(Command command) {
        command.setTaskList(taskList);
        return command.execute();
    }

    /**
     * Update the cached task list if it was modified by the previous command.
     *
     * @param taskList updated task list
     */
    private void updateTaskListIfPresent(TaskList taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }
}
