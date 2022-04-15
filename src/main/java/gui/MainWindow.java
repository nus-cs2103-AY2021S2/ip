package gui;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.InputStream;
import java.io.OutputStream;

public class MainWindow {

    private ScrollPane chatLog;
    private Scene scene;
    private TextAreaOutputStream textAreaOut;
    private TextFieldInputStream textFieldIn;

    public void createChatWindow() {

        textAreaOut = new TextAreaOutputStream();
        textFieldIn = new TextFieldInputStream();
        textAreaOut.textArea.setWrapText(true);

        chatLog = new ScrollPane();
        chatLog.setContent(textAreaOut.textArea);

        chatLog.heightProperty().addListener((observable, oldValue, newValue) -> {
            textAreaOut.textArea.setPrefHeight(chatLog.getHeight() - 5);
        });

        BorderPane chatLogLayout = new BorderPane();
        chatLogLayout.setMinWidth(200);
        chatLogLayout.setMinHeight(500);
        chatLogLayout.setCenter(chatLog);
        chatLogLayout.setBottom(textFieldIn.field);

        scene = new Scene(chatLogLayout);
    }

    public Scene getScene() {
        return scene;
    }

    public OutputStream getOutputStream() {
        return textAreaOut;
    }

    public InputStream getInputStream() {
        return textFieldIn;
    }
}
