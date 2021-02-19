package duke;

import duke.system.Parser;
import duke.system.view.Gui;
import duke.task.TaskList;
import javafx.application.Application;

public class Duke {
    private TaskList tasks;
    private Parser in;

    /**
     * Initiate Duke with default Parser and TaskList for it to function
     */
    public Duke() {
        in = new Parser();
        tasks = new TaskList();
    }

    /**
     * Get response for the user input
     *
     * @return the string to be displayed on the GUI
     */
    public String getResponse(String input, TaskList tasks) {
        in = new Parser(input);
        return in.print(tasks);
    }

    /**
     * Initiate the GUI interface
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
