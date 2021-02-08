import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class Main extends Application {

  private Duke duke = new Duke();
  private Image background = new Image(this.getClass().getResourceAsStream("c.jpg"));

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
      AnchorPane anchorPane = new AnchorPane();
      fxmlLoader.setRoot(anchorPane);
      anchorPane = fxmlLoader.load();
      Scene scene = new Scene(anchorPane);
      stage.setScene(scene);
      fxmlLoader.<MainWindow>getController().setDuke(duke);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Main() throws IOException {
  }
}