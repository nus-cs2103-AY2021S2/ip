package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.InvalidFileTaskTypeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;


public class Duke {
    private static final String FILE_PATH = "./storage/tasks.txt";
    private static final Storage STORAGE = new Storage(FILE_PATH);
    private static TaskList taskList;

    /**
     * Gets input from the user via the GUI and processes it.
     */
    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(taskList, STORAGE);
        Command command = parser.parse(input);
        String response = command.execute();
        return response;
    }

    /**
     * Initialises Duke by populating taskList with Tasks stored in local storage file.
     * @return message indicating status of file load.
     * @throws FileNotFoundException when file does not exist.
     * @throws InvalidFileTaskTypeException when an entry in the file has errors.
     */
    public String introduction() throws FileNotFoundException, InvalidFileTaskTypeException {
        String msg = "Hello! I'm Duke.\n";
        taskList = STORAGE.loadFromFile();

        if (taskList.getList().size() == 0) {
            msg += "You have no existing tasks!";
        } else {
            msg += "You have existing tasks! \nEnter 'list' to see your list of tasks!";
        }
        return msg;
    }
}
