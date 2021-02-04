import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {
    private Ui ui;
    private TaskList taskList;

    private static final String LOG_PATH = "./logs";

    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/qn_parrot.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ans_parrot.png"));

    public Duke() {
        Storage store = new Storage(LOG_PATH);
        this.taskList = new TaskList(store);
        this.ui = new Ui();
    }

    /**
     * Starts running Duke.
     * @return 0 for successful exit
     */
    public int dukeRunner() {
        Ui.printHello();

        while (true) {
            String userInput = ui.getInputFromUser();

            ParserOutput parserOutput = null;
            try {
                parserOutput = Parser.parse(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            ui.printHRule();

            if (parserOutput.isBye()) {
                break;
            }

            this.readParse(parserOutput);

            ui.printHRule();
        }

        Ui.printGoodbye();

        return 0;
    }

    private void readParse(ParserOutput parserOutput) {
        switch (parserOutput.getAction()) {
            case 1: //remove
                this.taskList.remove(parserOutput.getIndex());
                ui.printRemoved(parserOutput.getIndex());
                break;
            case 2: //done
                Task doneTask = this.taskList.get(parserOutput.getIndex());
                doneTask.markDone();
                this.taskList.set(parserOutput.getIndex(), doneTask);
                ui.printDone(doneTask);
                break;
            case 3: //add
                this.taskList.add(parserOutput.getTask());
                ui.printAdded(parserOutput.getTask(), this.taskList.getSize());
                break;
            case 4: //find
                List<Task> results = this.taskList.find(parserOutput.getSearchString());
                ui.printSearch(results, parserOutput.getSearchString());
                break;
            case 5: //list
                ui.printList(this.taskList);
                break;
            default:
                //TODO: add exception for this
        }
    }

    private String readParseGui(ParserOutput parserOutput) {
        switch (parserOutput.getAction()) {
            case 1: //remove
                this.taskList.remove(parserOutput.getIndex());
                return ui.printRemoved(parserOutput.getIndex());
            case 2: //done
                Task doneTask = this.taskList.get(parserOutput.getIndex());
                doneTask.markDone();
                this.taskList.set(parserOutput.getIndex(), doneTask);
                return ui.printDone(doneTask);
            case 3: //add
                this.taskList.add(parserOutput.getTask());
                return ui.printAdded(parserOutput.getTask(), this.taskList.getSize());
            case 4: //find
                List<Task> results = this.taskList.find(parserOutput.getSearchString());
                return ui.printSearch(results, parserOutput.getSearchString());
            case 5: //list
                return Ui.printList(this.taskList);
            default:
                return "Sorry, could you repeat please? ";
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene); //set up stage to show screen
        stage.show(); //render

        //Container for contents of chat
        scrollPane = new ScrollPane();
        dialogueContainer = new VBox();
        scrollPane.setContent(dialogueContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogueContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogueContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        ParserOutput parserOutput = null;
        try {
            parserOutput = Parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }

        if (parserOutput.isBye()) {
            return Ui.printGoodbye();
        }

        return readParseGui(parserOutput);
    }

    public static void main(String[] args) {
        new Duke().dukeRunner();
    }
}
