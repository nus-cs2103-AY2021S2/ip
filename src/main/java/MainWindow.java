import duke.Duke;
import duke.command.CommandResponse;
import duke.command.StatsCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image dukeImage = new Image(
            this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image userImage = new Image(
            this.getClass().getResourceAsStream("/images/DaUser.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(
                this.dialogContainer.heightProperty());
        this.dialogContainer.getChildren().add(
                DukeTextDialogBox.getDialogBox(this.duke.getGreeting(), this.dukeImage));
    }

    public void setDuke(Duke d) {
        this.duke = d;
    }

    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        CommandResponse response = this.duke.getResponse(input);

        this.dialogContainer.getChildren().add(
                UserTextDialogBox.getDialogBox(input, this.userImage));
        if (response.getCommandClass() == StatsCommand.class) {
            this.dialogContainer.getChildren().add(
                    DukePieChartDialogBox.getDialogBox(this.duke.getTaskList(), this.userImage));
        } else {
            this.dialogContainer.getChildren().add(
                    DukeTextDialogBox.getDialogBox(response.toString(), this.userImage));
        }
        this.userInput.clear();

        if (response.canExit()) {
            Platform.exit();
        }
    }
}
