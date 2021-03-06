package project;

import project.command.AddDeadline;
import project.command.AddEvent;
import project.command.AddTodo;
import project.command.DeleteCommand;
import project.command.DoneCommand;
import project.command.ExitCommand;
import project.command.FindCommand;
import project.command.HelpCommand;
import project.command.ListCommand;
import project.command.RemindCommand;
import project.common.UserCommands;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

/**
 * Handles application logic.
 */
public class OlafApp implements UserCommands {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates an instance of {@code OlafApp}.
     *
     * @param tasks All the tasks stored.
     * @param ui A {@code Ui} instance to handle test output.
     * @param storage A {@code Storage} instance to handle saving and loading tasks from local file.
     */
    OlafApp(TaskList tasks, Ui ui, Storage storage) {
        this.taskList = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Executes each session of the application.
     *
     * @param userInput
     */
    public String run(String userInput) {
        String command = userInput.toLowerCase().trim();

        // Solution adapted from https://github.com/nus-cs2103-AY2021S2/ip/pull/91/files
        if (command.equals(UserCommands.BYE)) {
            return new ExitCommand().execute(taskList, ui);
        } else if (command.equals(UserCommands.HELP)) {
            return new HelpCommand().execute(taskList, ui);
        } else if (command.equals(UserCommands.LIST)) {
            return new ListCommand().execute(taskList, ui);
        } else if (command.equals(UserCommands.REMIND)) {
            return new RemindCommand(command).execute(taskList, ui);
        } else if (command.startsWith(UserCommands.FIND)) {
            return new FindCommand(command).execute(taskList, ui);
        } else if (command.startsWith(UserCommands.DONE)) {
            return new DoneCommand(command).execute(taskList, ui, storage);
        } else if (command.startsWith(UserCommands.DELETE)) {
            return new DeleteCommand(command).execute(taskList, ui, storage);
        } else if (command.startsWith(UserCommands.TODO)) {
            return new AddTodo(command).execute(taskList, ui, storage);
        } else if (command.startsWith(UserCommands.DEADLINE)) {
            return new AddDeadline(command).execute(taskList, ui, storage);
        } else if (command.startsWith(UserCommands.EVENT)) {
            return new AddEvent(command).execute(taskList, ui, storage);
        } else {
            return ui.showFormatResponse("  Hmm sorry I don't understand :(\n"
                    + "  Type 'help' to find out how you can talk to me!\n");
        }
    }
}
