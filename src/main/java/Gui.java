import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * The type Gui.
 */
public class Gui extends Application {
    private HashMap<String, Image> imageMap;
    private Scene welcomeScene;
    private Scene dukeRunScene;
    private Button startButton;
    private Button sendButton;
    private TextField userInput;
    private Stage primaryStage;
    private VBox dialogContainer;
    private Image user;
    private Image bot;

    /**
     * Load resources from resources folder.
     */
    public void loadResources() {
        assert ((new File("src/main/resources/images/botDead.png")).exists()
            && (new File("src/main/resources/images/botHappy.png")).exists()
            && (new File("src/main/resources/images/botNormal.png")).exists())
                : "image files for bot do not exist";
        assert (new File("src/main/resources/images/userNormal.png")).exists()
                : "image file for user does not exist";
        assert (new File("src/main/resources/images/welcomeScreen.png")).exists()
                : "image file for welcome screen does not exist";

        this.imageMap = new HashMap<>();
        this.imageMap.put("botDead", new Image(this.getClass().getResourceAsStream(
                "/images/botDead.png")));
        this.imageMap.put("botHappy", new Image(this.getClass().getResourceAsStream(
                "/images/botHappy.png")));
        this.imageMap.put("botNormal", new Image(this.getClass().getResourceAsStream(
                "/images/botNormal.png")));
        this.imageMap.put("userNormal", new Image(this.getClass().getResourceAsStream(
                "/images/userNormal.png")));
        this.imageMap.put("welcomeScreen", new Image(this.getClass().getResourceAsStream(
                "/images/welcomeScreen.png")));

    }

    /**
     * Create welcome screen scene.
     *
     * @return the scene
     */
    public Scene welcomeCreate() {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        AnchorPane mainLayout = new AnchorPane();
        this.startButton = new Button("start");
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        startButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(startButton, 1.0);
        AnchorPane.setRightAnchor(startButton, 175.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        mainLayout.setPrefSize(400.0, 600.0);
        ImageView imageView = new ImageView(imageMap.get("welcomeScreen"));
        imageView.setFitHeight(550.0);
        imageView.setPreserveRatio(true);
        mainLayout.getChildren().addAll(scrollPane, startButton, imageView);
        return new Scene(mainLayout);


    }

    /**
     * Create duke main screen scene.
     *
     * @return the scene
     */
    public Scene dukeRunCreate() {
        ScrollPane scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        this.userInput = new TextField();
        this.sendButton = new Button("send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        this.bot = this.imageMap.get("botHappy");
        return new Scene(mainLayout);

    }

    /**
     * Stage set.
     *
     * @param scene the scene
     */
    public void stageSet(Scene scene) {
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Duke");
        this.primaryStage.setResizable(false);
        this.primaryStage.setMinHeight(600.0);
        this.primaryStage.setMinWidth(400.0);
        this.primaryStage.show();
    }

    private String getResponse(String userTest, Duke duke) {
        duke.setCurrentCommand(userTest);
        return duke.returnOutput();
    }

    /**
     * Handle user input.
     *
     * @param duke the duke
     */
    public void handleUserInput(Duke duke) {
        String userCommand = userInput.getText();
        Label userText = new Label(userCommand);

        String responseText = getResponse(userCommand, duke);
        Label dukeText = new Label(responseText);

        this.user = this.imageMap.get("userNormal");
        this.bot = this.imageMap.get("botNormal");
        if (duke.getHasError()) {
            this.bot = this.imageMap.get("botDead");
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(bot))
        );
        if (userCommand.equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadResources();
        this.primaryStage = stage;
        this.welcomeScene = welcomeCreate();
        this.dukeRunScene = dukeRunCreate();
            //String filePath = new File(".").getCanonicalPath();
            //filePath.concat("/data.txt");
            Duke duke = new Duke("data.txt");


        //Duke duke = new Duke("src/main/java/data.txt");
        stageSet(this.welcomeScene);

        this.startButton.setOnMouseClicked((event) -> {
            stageSet(this.dukeRunScene);
        });
        this.userInput.setOnAction((event) -> {
            handleUserInput(duke);
        });
        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput(duke);
        });
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(duke.lastOutListElement()),
                        new ImageView(this.bot))
        );

    }


}
