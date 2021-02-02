package gui;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainWindow {

    private ScrollPane chatLog;
    private TextField chatInput;
    private BorderPane chatLogLayout;
    private Scene scene;

    public void createChatWindow() {

        chatLog = new ScrollPane();
        chatInput = new TextField();

        chatLogLayout = new BorderPane();
        chatLogLayout.setMinWidth(200);
        chatLogLayout.setMinHeight(500);
        chatLogLayout.setCenter(chatLog);
        chatLogLayout.setBottom(chatInput);

        scene = new Scene(chatLogLayout);
    }

    public Scene getScene() {
        return scene;
    }
}
