import java.io.IOException;
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

  private Image userImage = new Image(this.getClass().getResourceAsStream("/images/B.JPG"));
  private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/A.JPG"));



  @FXML
  public void initialize() {
    scrollPane.setStyle("-fx-border-color: #0000ff");
    scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
  }

  public void setDuke(Duke d) {
    duke = d;
  }


  /**
     * Creates two dialog boxes, containing the user input and the reply
     * The user input is cleared after processing.
  */
  @FXML
  private void handleUserInput() throws IOException {
    String input = userInput.getText();
    String response = duke.getResponse(input);
    dialogContainer.getChildren().addAll(
        DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
    );
    userInput.clear();
  }

}
