package duke;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ListCommand;


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
        this.storage = new Storage(DIRECTORY);
        this.tasks = new TaskList();
        storage.loadTasks(tasks);
    }

    /**
     * Function method to be used in main to start Duke
     *
     * @throws DukeException
     */

    public void run() throws DukeException {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command executable = Parser.parse(input);
                String output = executable.execute(tasks, ui, storage);

                if (executable.isEndOfProgram()) {
                    isExit = !executable.isEndOfProgram();
                    System.exit(0);
                }
            } catch (DukeException e) {
                e.getMessage();
            }
        }
    }
}
