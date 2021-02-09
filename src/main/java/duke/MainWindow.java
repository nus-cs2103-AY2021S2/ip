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
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends VBox {

    @FXML
    private VBox console;
    @FXML
    private MenuBar menu;
    @FXML
    private Menu change;
    @FXML
    private Label changeButton;
    @FXML
    private Menu createTodo;
    @FXML
    private Label todoButton;
    @FXML
    private Menu createDeadline;
    @FXML
    private Menu createEvent;
    @FXML
    private Menu guiDelete;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private VBox consoleWindow;
    @FXML
    private TextField userInput;

    private Parser parser;

    private Stage stage;

    @FXML
    public void initialize() {
        consoleWindow.getChildren().addAll(new Label(Ui.welcome()));
        scrollPane.vvalueProperty().bind(consoleWindow.heightProperty());
        userInput.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
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
        this.parser = p;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void parseCommand() {
        String command = userInput.getText();
        consoleWindow.getChildren().addAll(new Label(command), new Label(parser.parser(command)));
        //text.setText(parser.chat(command));
        userInput.clear();
        if (command.equals("bye")) {
            Platform.exit();
        }
    }

    @FXML
    private void createTodo() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/TodoWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
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
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/DeleteWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
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
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/DeadlineWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
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
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/EventWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            final Stage dialog = new Stage();
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
            System.out.println("test");
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/AltWindow.fxml"));
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
