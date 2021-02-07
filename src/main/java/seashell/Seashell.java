package seashell;

import java.util.Scanner;

import javafx.scene.control.Label;
import seashell.command.Command;

public class Seashell {

    public TaskList taskListObj;
    public final SaveHandler saveHandler;
    private final Ui ui;

    /**
     * Create a new Seashell instance
     */
    public Seashell() {
        this.saveHandler = new SaveHandler();
        this.taskListObj = new TaskList(this.saveHandler.loadSave());
        this.ui = new Ui();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskListObj, this.saveHandler);
        } catch (SeashellException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

//    protected void start() {
//        ui.showWelcome();
//        Scanner sc = new Scanner(System.in);
//        Parser parser = new Parser();
//        boolean isExit = false;
//        while (!isExit) {
//            String input = sc.nextLine();
//            try {
//                Command command = parser.parse(input);
//                command.execute(this.taskListObj, this.saveHandler);
//                isExit = command.isExit();
//            } catch (SeashellException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }
}
