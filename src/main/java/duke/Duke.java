package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;



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


    public String getResponse(String input) throws DukeException{
        try {
            Command executableCommand = Parser.parse(input);
            String output = executableCommand.execute(tasks, ui, storage);
            if (executableCommand.isEndOfProgram()){
                System.exit(0);
            }
            return output;
        } catch (DukeException e){
            String message = e.toString();
            ui.formatAndPrintType1(message);
            return message;
        }

    }
}
