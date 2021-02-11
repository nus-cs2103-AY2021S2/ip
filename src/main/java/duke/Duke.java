package duke;

import java.io.File;

import duke.command.Command;
import duke.tasks.TaskList;
import javafx.application.Platform;

/**
 *  Juke is a chatbot that helps users keep track of tasks.
 *
 *  The chatbot supports, todos, events and deadlines,
 *      todos: add to do task to a list
 *      events: adds task to a list with a specific time for the event with "/at"
 *      deadlines: adds task to a list with a specific deadline with "/by"
 *
 *      The done command followed by an integer x checks off  task x.
 *      The chatbot supports deletion of tasks with the "delete" command
 *
 *      The chatbot will throw exceptions for invalid inputs.
 * @author branzuelajohn
 * @version CS2103T AY20/21 Semester 2, Individual Project
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke Object.
     */
    public Duke() {
        ui = new Ui();
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "data" + File.separator + "duke.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadAllTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Constructor method to create a Duke object.
     * @param filePath the path file to duke text file in the hard drive.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadAllTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public String greet() {
        return "Hello! I am Juke, your personal assistant, how can I help you today?";
    }

}
