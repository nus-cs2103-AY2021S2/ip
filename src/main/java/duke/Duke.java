package duke;

import java.util.ArrayList;

import duke.command.Command;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {

    private static final String FILE_PATH = "data/task.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke() {}

    /**
     * Creates a new instance of 'Duke' bot
     * @param filePath path to file containing saved tasks
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load(), this.storage);
        this.parser = new Parser();
        this.ui = new Ui("Olly");
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Olly Chatbot");
        Label label1 = new Label("Welcome to Olly Chatbot, chat away!");

        // Setup storage, parser, tasks, ui
        this.storage = new Storage(FILE_PATH);
        this.parser = new Parser();
        this.tasks = new TaskList(this.storage.load(), this.storage);
        this.ui = new Ui("Olly");


        // Setup GUI
        TextField textField = new TextField();
        ListView<String> listView = new ListView<String>();

        // List View
        listView.getItems().add("Olly: Welcome! Talk to me, i'm bored!");

        // Text Field
        textField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER) && !textField.getText().equals("")) {
                listView.getItems().add("Me: " + textField.getText());
                try {
                    Command c = this.parser.parse(textField.getText());
                    ArrayList<String> responses = c.execute(tasks, ui, storage);
                    for (String response : responses) {
                        listView.getItems().add(response);
                    }
                } catch (DukeException dukeEx) {
                    // fall through
                    listView.getItems().add(ui.speak(dukeEx.toString()));
                }
                textField.setText("");
            }
        });

        VBox layout1 = new VBox(20);
        layout1.setPadding(new Insets(5));
        layout1.getChildren().addAll(label1, listView, textField);
        Scene scene1 = new Scene(layout1, 600, 500);
        stage.setScene(scene1);
        textField.requestFocus();
        stage.show();
    }

    /**
     * Spins up the bot and allow execution from users
     */
    public void run() {
    //        Scanner sc = new Scanner(System.in);
    //        ui.speak("Hey! Welcome to the chatbot. What can I do for you today?");
    //
    //        boolean isExit = false;
    //        while (!isExit) {
    //            String input = ui.readCommand();
    //            try {
    //                Command c = this.parser.parse(input);
    //                c.execute(tasks, ui, storage);
    //                isExit = c.isExit();
    //            } catch (DukeException dukeEx) {
    //                dukeEx.printStackTrace();
    //            }
    //        }
    }

    public static void main(String[] args) {
        new Duke("data/task.txt").run();
    }
}
