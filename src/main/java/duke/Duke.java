package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Duke extends Application {

    private static final String FILE_PATH = "data/task.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    private static final String DEFAULT_CONTROL_INNER_BACKGROUND = "derive(-fx-base,80%)";
    private static final String OLLY_BACKGROUND_COLOR = "#53edd3";
    private static final String ME_BACKGROUND_COLOR = "#fcac62";

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

        // List View Methods
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + DEFAULT_CONTROL_INNER_BACKGROUND + ";");
                        } else {
                            setText(item);
                            if (item.startsWith("Me")) {
                                setStyle("-fx-control-inner-background: " + ME_BACKGROUND_COLOR);
                                // setAlignment(Pos.BASELINE_RIGHT);
                                // setStyle("-fx-alignment: right");
                            } else if (item.startsWith("Olly: ALAMAK")) {
                                setStyle("-fx-control-inner-background: #ed6853");
                            } else {
                                setStyle("-fx-control-inner-background: " + OLLY_BACKGROUND_COLOR);
                            }
                        }

                        setPadding(new Insets(10));
                    }
                };
            }
        });

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
}
