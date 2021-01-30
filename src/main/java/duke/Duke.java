package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.CommandException;

/**
 * Duke handles the commands from the GUI
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
     * Runs the command from the user
     * @param userInput the input from the user
     * @return a String of the reply to the userInput
     */
    public String runCommand(String userInput) {
        try {
            Command cmd = p.parse(userInput);
            return ui.reply(cmd.execute(ui, s, list));
        } catch (CommandException e) {
            return ui.showError(e.getMessage());
        } catch (IOException | NumberFormatException e) {
            return ui.showError("Please enter a valid number!");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("You don't have a task at that index!");
        }
    }

    /**
     * Welcome message from duke
     * @return a welcome message from duke
     */
    public String welcome() {
        return ui.welcome();
    }
}
