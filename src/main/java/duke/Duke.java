package duke;

import duke.tasks.TaskList;
import duke.command.Command;

import java.io.File;

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
     * Constructor method to create a Duke object.
     * @param filePath the path file to duke text file in the hard drive.
     */
    public Duke(String filePath) {
            ui = new Ui();
            storage = new Storage(filePath);
            try {
                tasks = new TaskList(storage.loadAllTasks());
            } catch (DukeException e) {
                System.out.println(tasks);
                ui.showLoadingError();
                tasks = new TaskList();
            }
        }

    /**
     * Runs the Duke AI bot.
     */
    public void run() {
            ui.showWelcome();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String command = ui.readCommand();
                    ui.printHorizontalLine();
                    Command c = Parser.parse(command);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.printHorizontalLine();
                }
            }
        }

        public static void main(String[] args) {
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + File.separator + "data" + File.separator + "duke.txt";

            new Duke(filePath).run();
        }

}
