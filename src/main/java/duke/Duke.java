package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.AddCommand;
import duke.command.CommandWrite;
import duke.command.DefaultCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ICommand;
import duke.command.PrintListCommand;
import duke.command.SetPriorityCommand;
import duke.task.DeadlineFactory;
import duke.task.EventFactory;
import duke.task.ToDoFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for Duke app.
 */
public class Duke extends Application {
    private Storage storage;
    private CommandMap commands;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor method for Duke app. Initialises main components.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.ui = new Ui(this);
        this.commands = new CommandMap(new DefaultCommand(this.ui));
    }

    /**
     * Starts the application. Not to be called explicitly.
     * @param stage Stage to be displayed
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(ui);
        stage.setTitle("Duke");
        stage.setScene(scene);
        initialiseCommandMap();
        this.run();
        stage.show();
    }

    private void run() {
        try {
            this.storage.read();
        } catch (FileNotFoundException e) {
            ui.createDukeDialog("duke.txt not found.\nFile will be created when a task is added.");
        } catch (IOException e) {
            ui.handleError(e);
        } catch (IllegalArgumentException e) {
            ui.handleError(e);
        }
        ui.handleIntro();
    }

    /**
     * Passes the input to parser to be parsed.
     * @param input input to be parsed.
     */
    public void handleInput(String input) {
        String[] inputArray = Parser.parseInitial(input);
        if (inputArray.length == 2) {
            commands.get(inputArray[0]).execute(inputArray[1]);
        } else if (inputArray.length == 1) {
            //for commands with only one word, will give error msg if command requires more than 1.
            commands.get(inputArray[0]).execute(" ");
        }
    }

    private void initialiseCommandMap() {
        ICommand doneCommand = new DoneCommand(ui, taskList);
        doneCommand = new CommandWrite(ui, storage, doneCommand);

        ICommand listCommand = new PrintListCommand(ui, taskList);

        ICommand exitCommand = new ExitCommand(ui);

        ICommand eventCommand = new AddCommand(ui, taskList, new EventFactory());
        eventCommand = new CommandWrite(ui, storage, eventCommand);

        ICommand deadlineCommand = new AddCommand(ui, taskList, new DeadlineFactory());
        deadlineCommand = new CommandWrite(ui, storage, deadlineCommand);

        ICommand toDoCommand = new AddCommand(ui, taskList, new ToDoFactory());
        toDoCommand = new CommandWrite(ui, storage, toDoCommand);

        ICommand deleteCommand = new DeleteCommand(ui, taskList);
        deleteCommand = new CommandWrite(ui, storage, deleteCommand);

        ICommand findCommand = new FindCommand(ui, taskList);

        ICommand setPriorityCommand = new SetPriorityCommand(ui, taskList);

        commands.add("done", doneCommand);
        commands.add("list", listCommand);
        commands.add("bye", exitCommand);
        commands.add("event", eventCommand);
        commands.add("todo", toDoCommand);
        commands.add("deadline", deadlineCommand);
        commands.add("delete", deleteCommand);
        commands.add("find", findCommand);
        commands.add("priority", setPriorityCommand);
    }
}
