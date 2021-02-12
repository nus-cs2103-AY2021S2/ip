
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

    public Duckie() throws IOException {
        ui = new Ui();
        storage = new Storage("duckie.txt");
        try {
            lst = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            //ui.showLoadingError();
            lst = new TaskList();
        }
    }

    /**
     * Constructor method.
     * @param filePath to the saved file
     * @throws IOException if user IO is incorrect
     */
    public Duckie(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            lst = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            //ui.showLoadingError();
            lst = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = null;
            try {
                cmd = Parser.parse(input);
            } catch (DuckieException e) {
                e.printStackTrace();
            }
            return cmd.execute(lst, ui, storage);
        } catch (DuckieException e) {
            return e.getMessage();
        }
    }


    /**
     * Run method to run Duckie.
     * @throws IOException if user IO is incorrect
     */
    public void run() throws IOException {
        storage.loadTasks();
        ui.startMessage();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String fullInput = ui.readInput();
                ui.customLine();
                Command cmd = Parser.parse(fullInput);
                cmd.execute(lst, ui, storage);
                isEnd = cmd.isEnd();
            } catch (DuckieException e) {
                e.printStackTrace();
            }
        }
        storage.saveTasks(lst.getTaskList());
    }

    /**
     * Main method to drive Duckie.
     * @param args command line arguments
     * @throws IOException if user IO is incorrect
     */
    public static void main(String[] args) throws IOException {
        new Duckie("duckie.txt").run();
    }
}