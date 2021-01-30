package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.CommandException;


/**
 * The entry point for the task helper Duke
 *
 * @author Foo Chuan Le, Nicholas
 */
public class Duke {
    private final Parser p;
    private final Storage s;
    private TaskList list;
    private final Ui ui;

    /**
     * Constructor for Duke, to initialise UI, Storage and Parser
     * tries to load TaskList from storage, else start a new empty TaskList
     */
    public Duke() {
        ui = new Ui();
        s = new Storage();
        p = new Parser();
        try {
            list = new TaskList(s.loadData());
        } catch (FileNotFoundException e) {
            list = new TaskList();
        }
    }

    /**
     * Runs the Duke bot
     * Takes user input by line and executes the corresponding command,
     * or prompts the user if the command is not understood with ui.showError()
     */
    public void run() {
        ui.welcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                Command cmd = p.parse(ui.getInput());
                cmd.execute(ui, s, list);
                isBye = cmd.getIsBye();

            } catch (CommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException | NumberFormatException e) {
                ui.showError("Please enter a valid number!");
            } catch (IndexOutOfBoundsException e) {
                ui.showError("You don't have a task at that index!");
            }
        }
    }

    /**
     * Starting point for Duke
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
