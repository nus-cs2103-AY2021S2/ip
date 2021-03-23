package seashell;

import javafx.scene.control.Label;
import seashell.command.Command;
import seashell.exception.SeashellException;
import seashell.parser.Parser;
import seashell.storage.SaveHandler;

public class Seashell {

    public final SaveHandler saveHandler;
    private TaskList taskListObj;

    /**
     * Create a new Seashell instance with task list loaded from save file
     */
    public Seashell() {
        this.saveHandler = new SaveHandler();
        this.taskListObj = new TaskList(this.saveHandler.loadSave());
    }

    /**
     * Take in user input and executes the command, then return the response from Seashell as a string
     * @param input command input by user
     * @return response to user input from Seashell
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.taskListObj);
            this.saveHandler.updateSaveFile(this.taskListObj);
            return response;
        } catch (SeashellException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
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
