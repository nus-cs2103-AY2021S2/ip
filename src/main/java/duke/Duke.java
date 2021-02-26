package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;


/**
 * Duke is the name of program which is a CLI application that
 * reads in user tasks and saves them. Duke helps the user to
 * track their tasks, allowing the user to mark them when they
 * have been completed.
 */
public class Duke {
    public static final String DIRECTORY = System.getProperty("user.dir");
    public static TaskList tasks;
    private Storage storage;
    private final Ui ui;

    /**
     * Constructor for Duke object
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.DIRECTORY);
        this.tasks = new TaskList();
        storage.loadTasks(tasks);
    }

    /**
     * Sets a delayed timeout when exiting the program.
     * @author Shilo
     * Adapted https://gist.github.com/Shilo/207c7ba4a604b7811b77ff17be8580f3
     *
     * @param runnable Function to be executed after the timeout.
     * @param delay Delay before exiting the program in ms.
     */
    public static void setTimeOut(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    /**
     * Runs the programme using a Duke object.
     *
     * @param input user input
     * @return String containing the output based on the respective input/command
     * @throws DukeException
     */
    public String getResponse(String input) throws DukeException{
        try {
            Command executableCommand = Parser.parse(input);
            String output = executableCommand.execute(tasks, ui, storage);
            if (executableCommand.isEndOfProgram()){
                setTimeOut(() -> {
                   Platform.exit();
                   System.exit(0);
                }, 1500);
            }
            return output;
        } catch (DukeException e){
            String message = e.toString();
            ui.formatAndPrintType(message);
            return message;
        }
    }
}
