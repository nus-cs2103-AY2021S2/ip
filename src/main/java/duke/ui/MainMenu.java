package duke.ui;


import duke.Duke;
import duke.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Encapsulates an MainMenu class that deals with displaying an alert UI.
 */
public class MainMenu {

    @FXML
    private VBox logoBox;

    @FXML
    private Label dukeLogo;

    @FXML
    private Button startButton;


    @FXML
    private void initialize() {
        dukeLogo.setText(Ui.LOGO);
    }



    @FXML
    private void switchWindow() {
        try {
            Stage stage = (Stage)startButton.getScene().getWindow();
            Parent root = FXMLLoader.load(duke.Main.class.getResource("/view/MainWindow.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}