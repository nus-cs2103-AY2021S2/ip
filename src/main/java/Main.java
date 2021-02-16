import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {

  private Duke duke = new Duke();


  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
      MainWindow mainWindow = new MainWindow();
      fxmlLoader.setRoot(mainWindow);
      mainWindow = fxmlLoader.load();
      Scene scene = new Scene(mainWindow);
      stage.setScene(scene);
      stage.show();
      VBox dialogContainer = (VBox) scene.lookup("#dialogContainer");
      fxmlLoader.<MainWindow>getController().setDuke(duke);
      String greeting = duke.run();
      DialogBox helloDialog = DialogBox.getDukeDialog(greeting,
              new Image(this.getClass().getResourceAsStream("/images/A.JPG")));
      dialogContainer.getChildren().addAll(helloDialog);
    } catch (IOException | DescriptionError e) {
      e.printStackTrace();
    }
  }

  public Main() throws IOException {
  }
}