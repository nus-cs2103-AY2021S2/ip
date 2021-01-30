package Duke;

import Duke.exception.EmptyDescription;
import Duke.exception.InvalidTypeOfTask;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke program maintains a taskList for user to track tasks.
 * Reads user input tasks(todo, event, deadline).
 * Able to perform add, delete, markasDone tasks.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = storage.load();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    /**
     * Initialise scanner.
     *
     * @param args
     */
    public static void main(String[] args){
        Duke duke = new Duke();
        duke.execute();
    }

    /**
     * Start Duke chat services.
     */
    public void execute() {
        ui.Greet();
        Boolean shouldExit = false;
        Scanner s = new Scanner(System.in);

        while(!shouldExit && s.hasNextLine()){
            try {
                taskList = storage.load();
                taskList = ui.readCommand(taskList, s);
                shouldExit = ui.getExit();
                storage.save(taskList.getTasks());



            } catch (EmptyDescription e) {
                ui.enclose(e.toString());
            } catch (InvalidTypeOfTask e) {
                ui.enclose(e.toString());
            } catch (IOException e) {

            }
        }
        ui.exit();
    }
}
