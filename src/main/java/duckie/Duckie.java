package duckie;

import java.io.IOException;

import classes.DuckieException;
import classes.Parser;
import classes.Storage;
import classes.TaskList;
import classes.Ui;
import command.Command;



/**
 * Main class.
 */
public class Duckie {
    private Storage storage;
    private TaskList lst;
    private Ui ui;

    /**
     * Constructor method.
     * @throws IOException if user IO is incorrect
     */
    public Duckie() throws IOException {
        ui = new Ui();
        storage = new Storage("duckie.txt");
        try {
            lst = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            lst = new TaskList();
        }
    }

    /**
     * Method for systemt to respond to user input for GUI.
     * @param input user input as a String
     * @return either command executed or error message
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.execute(lst, ui, storage);
        } catch (DuckieException e) {
            return ui.showErrorMessage(e);
        }
    }
}
