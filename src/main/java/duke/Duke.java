package duke;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.fileSaver.FileSaver;
import duke.task.TaskList;

public class Duke{
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return ui.getResponse(input, tasks, fs);
    }

    public String greeting() {
        return ui.greetingMessage();
    }
}
