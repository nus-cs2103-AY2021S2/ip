package duke;

import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;


public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke/tasks.txt";
    private static final Storage STORAGE = new Storage(FILE_PATH);
    private static TaskList taskList;

    private boolean isExit = false;

    /**
     * Gets input from the user via the GUI and processes it.
     */
    public String getResponse(String input) {
        try {
            Parser p = new Parser(taskList, STORAGE);
            Command c = p.parse(input);
            String output = c.execute();
            this.isExit = c.isExit();
            return output;
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
            return "Cannot access file at specified location.\n" + e.getMessage();
        } catch (InvalidTaskTypeException e) {
            taskList = new TaskList();
            return "Erroneous task type in file. Please check your file again!";
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
