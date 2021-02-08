package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;


public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke/tasks.txt";
    private static final Storage STORAGE = new Storage(FILE_PATH);
    private static TaskList taskList;

    /**
     * Gets input from the user via the GUI and processes it.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(taskList, STORAGE);
            Command command = parser.parse(input);
            String response = command.execute();
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialises Duke by populating taskList with Tasks stored in local storage file.
     *
     * @return message indicating status of file load.
     */
    public String introduction() {
        try {
            String msg = "Hello! I'm Duke.\n";
            taskList = STORAGE.loadFromFile();

            if (taskList.getList().size() == 0) {
                msg += "You have no existing tasks!";
            } else {
                msg += "You have existing tasks! \nEnter 'list' to see your list of tasks!";
            }
            return msg;
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
            String cannotAccessFileMsg = "Cannot access file at specified location.\n" + e.getMessage();
            return cannotAccessFileMsg;
        }
    }
}
