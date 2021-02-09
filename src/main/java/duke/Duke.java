package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class Duke extends Application {

    private Parser parser = new Parser();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setParser(this.parser);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        /*
        BorderPane dialogWindow = new BorderPane();
        dialogWindow.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));
        //Color.BLACK

        Group root = new Group();

        Scene scene = new Scene(dialogWindow, 500, 500);

        // setting color to the scene
        scene.setFill(Color.BLACK);

        //Setting the title to Stage.
        stage.setTitle("DUKE");

        final TextField userText = new TextField();
        userText.setBackground(new Background(new BackgroundFill(Paint.valueOf("white"), null, null)));
        userText.setText("awaiting your command...");
        userText.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    if (userText.getText().equals("awaiting your command...")) {
                        userText.setText("");
                    }
                }
                else
                {
                    if (userText.getText().equals("")) {
                        userText.setText("awaiting your command...");
                    }
                }
            }
        });
        dialogWindow.setBottom(userText); //attach the text field to the scene.

        Label text = new Label(Ui.welcome());
        text.setTextFill(Color.GREY);

        VBox vbox = new VBox(text);
        vbox.setAlignment(Pos.BOTTOM_LEFT);
        vbox.setMinHeight(470);
        vbox.setMinWidth(450);
        vbox.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));

        ScrollPane display = new ScrollPane();
        display.setContent(vbox);
        display.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //display.vvalueProperty().bind(text.heightProperty());
        display.setStyle("-fx-background: rgb(0, 0, 0);\n -fx-background-color: rgb(0, 0, 0)");

        dialogWindow.setCenter(display);
        dialogWindow.setAlignment(display, Pos.BOTTOM_RIGHT);

        vbox.heightProperty().addListener((observable) -> display.setVvalue(1.0));

        Parser parser = new Parser();

        display.requestFocus();

        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String command = userText.getText();
                vbox.getChildren().addAll(new Label(command), new Label(parser.parser(command)));
                //text.setText(parser.chat(command));
                userText.clear();
                display.snapPositionY(500);
                if (command.equals("bye")) {
                    Platform.exit();
                }
            }
        };

        userText.setOnAction(event);

        //Adding the scene to Stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

         */
}
