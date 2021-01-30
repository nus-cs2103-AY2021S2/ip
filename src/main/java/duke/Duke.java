package duke;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.control.Label;

public class Duke {
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/image1.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/image2.jpg"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("./src/main/data/Data.txt");
        taskManager = new TaskManager();
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.parseToStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            ui.showError("Storage file not found!");
        }
    }

    /**
     * Handles the user interaction and execution of commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Internal error! Storage file not in the correct format.");
            }
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return input;
    }

    //public static void main(String[] args) {
    //    new Duke("./src/main/data/Data.txt").run();
    //}
}
