package lihua.main;

import lihua.commands.Command;
import lihua.commands.CommandResult;
import lihua.commands.ExitCommand;
import lihua.parser.Parser;
import lihua.storage.Storage;
import lihua.tasks.Tasks;
import lihua.ui.Ui;

// Design idea adapted from https://github.com/se-edu/addressbook-level2
public class Lihua {
    /** Task list */
    private Tasks tasks;
    /** Storage object */
    private Storage storage;
    /** Ui object */
    private Ui ui;

    /**
     * Runs the application.
     *
     * @param args Lunch arguments of the main method.
     */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    public String getResponse(String input) {
        return "Lihua heard: " + input;
    }

    /**
     * Runs command loop until the user inputs a exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseUserInput(userInput);
            CommandResult result = executeCommand(command);
            ui.showFeedbackToUser(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command argument. If successful then saves the current task list.
     * If there are unchecked exception then shows user the error message and throws an RuntimeException.
     * Throwing RuntimeException is because the user would not
     * be expected to handle the unchecked exception him/herself.
     *
     * @param command The command to be executed.
     * @return The CommandResult of the command's execution.
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            CommandResult result = command.execute();
            storage.saveTasks(tasks);
            return result;
        } catch (Exception e) {
            ui.showFeedbackToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the application.
     *
     * @param args The launch arguments of the main method.
     */
    private void start(String[] args) {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
            ui.printHello();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exits the application with status code 0
     */
    private void exit() {
        System.exit(0);
    }
}
