import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Mister Duke is an interactive chat bot that
 * keep track of your tasks, deadlines and events
 * in a list.
 * Some things Mister Duke is good at:
 * - listing the tasks on your list
 * - marking specified tasks as done
 * - removing specified tasks from the list
 * - find matching tasks given a search word/phrase
 *
 * @author Shaelyn
 * @version CS2103T 20/21 Semester 2, Individual Project
 */

public class MisterDuke {

    private static Ui ui = new Ui();
    private static Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Constructor method.
     */
    public MisterDuke() {
        try {
            this.storage = new Storage("duke.txt");
            tasks = new ArrayList<Task>(storage.load());
        } catch (FileNotFoundException e) {
            ui.showDefaultError(e);
            tasks = new ArrayList<Task>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mister Duke's response to user input.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            return cmd.executeCommand(ui, storage, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}