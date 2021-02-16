package duke;
import duke.exception.DukeException;
import duke.fileSaver.FileSaver;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @param tasks is the task list containing the task inputs and handle the logic
 * @param ui returns the output string for duke
 * @param fs is the filesaver that save file to text
 * @author WangYihe
 * @author E0424695
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private FileSaver fs;

    public Duke() {
        fs = new FileSaver("data", "duke.txt");
        ui = new Ui();
        tasks = new TaskList();
        try {
            fs.load(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Get response from UI
     */
    public String getResponse(String input) {
        return ui.getResponse(input, tasks, fs);
    }

    /**
     * Print greeting message
     */
    public String greeting() {
        return ui.greeting();
    }
}
