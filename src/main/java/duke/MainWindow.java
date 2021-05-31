package duke;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends VBox {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox consoleWindow;
    @FXML
    private TextField userInput;

    private Parser parser;
    private Stage stage;

    @FXML
    public void initialize() {
        Label welcome = new Label(Ui.welcome());
        welcome.setTextFill(Color.GREEN);
        consoleWindow.getChildren().addAll(welcome);
        scrollPane.vvalueProperty().bind(consoleWindow.heightProperty());
        userInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                                Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    if (userInput.getText().equals("awaiting your command...")) {
                        userInput.setText("");
                    }
                }
                else
                {
                    if (userInput.getText().equals("")) {
                        userInput.setText("awaiting your command...");
                    }
                }
            }
        });
        scrollPane.requestFocus();
    }

    public void setParser(Parser p) {
        assert(p != null);
        this.parser = p;
    }

    public void setStage(Stage s) {
        assert(s != null);
        this.stage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void parseCommand() {
        String command = userInput.getText();
        Label output = new Label(parser.parse(command));
        output.setTextFill(Color.GREEN);
        consoleWindow.getChildren().addAll(new Label("\n" + command), output);
        userInput.clear();
        if (command.equals("bye")) {
            Platform.exit();
        }
    }

    @FXML
    private void createTodo() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/view/TodoWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
            dialog.setTitle("Create Todo");
            dialog.setScene(scene);
            fxmlLoader.<TodoWindow>getController().setParser(this.parser);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createDelete() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/view/DeleteWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
            dialog.setTitle("Delete");
            dialog.setScene(scene);
            fxmlLoader.<DeleteWindow>getController().setParser(this.parser);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createDeadline() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/view/DeadlineWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
            dialog.setTitle("Create Deadline");
            dialog.setScene(scene);
            fxmlLoader.<DeadlineWindow>getController().setParser(this.parser);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createEvent() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/view/EventWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
            dialog.setTitle("Create Event");
            dialog.setScene(scene);
            fxmlLoader.<EventWindow>getController().setParser(this.parser);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Duke.class.getResource("/view/AltWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            this.stage.setScene(scene);
            fxmlLoader.<AltWindow>getController().setParser(this.parser);
            fxmlLoader.<AltWindow>getController().setStage(this.stage);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
