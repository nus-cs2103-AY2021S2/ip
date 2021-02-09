package duckie;

import main.java.classes.DuckieException;
import main.java.classes.Parser;
import main.java.classes.Storage;
import main.java.classes.TaskList;
import main.java.classes.Ui;
import main.java.command.Command;

import java.io.IOException;

/**
 * Main class.
 */
public class Duckie{
    private Storage storage;
    private TaskList lst;
    private Ui ui;

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
        ui.endMessage();
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