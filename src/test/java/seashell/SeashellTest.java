package seashell;

import javafx.scene.control.Label;
import seashell.command.Command;
import seashell.exception.SeashellException;
import seashell.parser.Parser;
import seashell.storage.SaveHandler;
import seashell.ui.Ui;

public class SeashellTest {

    public TaskList taskListObj;
    public final SaveHandler saveHandler;
    private final Ui ui;

    /**
     * Create a new Seashell instance
     */
    public SeashellTest() {
        this.saveHandler = new SaveHandler();
        this.taskListObj = new TaskList();
        this.ui = new Ui();
    }

    public SeashellTest(TaskList tasklist) {
        this.saveHandler = new SaveHandler();
        this.taskListObj = tasklist;
        this.ui = new Ui();
    }

    /**
     * Take in user input and executes the command, then return the response from Seashell as a string
     * @param input command input by user
     * @return response to user input from Seashell
     */
    public String getResponse(String input) throws SeashellException {
        Command command = Parser.parse(input);
        String response = command.execute(this.taskListObj, this.saveHandler);
        return response;
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}