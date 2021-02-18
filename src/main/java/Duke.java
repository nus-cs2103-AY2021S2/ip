import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Duke extends Application {
    private static final String LOG_PATH = "./logs";

    private Ui ui;
    private TaskList taskList;
    private boolean testEnv;

    private ScrollPane scrollPane;
    private VBox dialogueContainer;
    private TextField userInput;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/qn_parrot.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ans_parrot.png"));

    public Duke() {
        this(LOG_PATH, false);
    }

    /**
     * Constructor for Duke.
     * @param path Path for storage file
     * @param testEnv Whether Duke is being created for testing purposes, testDuke only runs if this is true
     */
    public Duke(String path, boolean testEnv) {
        Storage store = new Storage(path);
        this.taskList = new TaskList(store);
        this.ui = new Ui();
        this.testEnv = testEnv;
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
            List<Task> removedTasks = this.taskList.remove(parserOutput.getIndex());
            ui.printRemoved(removedTasks);
            break;
        case 2: //done
            List<Task> doneTasks = this.taskList.get(parserOutput.getIndex());
            for (Task t : doneTasks) {
                t.markDone();
            }
            this.taskList.set(parserOutput.getIndex(), doneTasks);
            ui.printDone(doneTasks);
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
            Ui.printList(this.taskList);
            break;
        case 6: //piped
            int[] indexes = getPipeInput(parserOutput);
            int nextAction = parserOutput.getNextAction();
            if (nextAction == 1) {
                readParse(ParserOutput.removeOutput(indexes));
            } else if (nextAction == 2) {
                readParse((ParserOutput.doneOutput(indexes)));
            } else {
                ui.printSorry();
            }
            break;
        default:
            ui.printSorry();
        }
    }

    private int[] getPipeInput(ParserOutput in) {
        if (in.getPipeInput().getAction() == 4) {
            return this.taskList.findIndex(in.getPipeInput().getSearchString());
        } else if (in.getPipeInput().getAction() == 5) {
            return this.taskList.listIndex();
        } else {
            return null; //TODO: add exception for this
        }
    }

    private String readParseGui(ParserOutput parserOutput) {
        switch (parserOutput.getAction()) {
        case 1: //remove
            List<Task> removedTasks = this.taskList.remove(parserOutput.getIndex());
            return ui.printRemoved(removedTasks);
        case 2: //done
            List<Task> doneTasks = this.taskList.get(parserOutput.getIndex());
            for (Task t : doneTasks) {
                t.markDone();
            }
            this.taskList.set(parserOutput.getIndex(), doneTasks);
            return ui.printDone(doneTasks);
        case 3: //add
            this.taskList.add(parserOutput.getTask());
            return ui.printAdded(parserOutput.getTask(), this.taskList.getSize());
        case 4: //find
            List<Task> results = this.taskList.find(parserOutput.getSearchString());
            return ui.printSearch(results, parserOutput.getSearchString());
        case 5: //list
            return Ui.printList(this.taskList);
        case 6: //piped
            int[] indexes = getPipeInput(parserOutput);
            int nextAction = parserOutput.getNextAction();
            if (nextAction == 1) {
                return readParseGui(ParserOutput.removeOutput(indexes));
            } else if (nextAction == 2) {
                return readParseGui((ParserOutput.doneOutput(indexes)));
            } else {
                return ui.printSorry();
            }
        default:
            return ui.printSorry();
        }
    }

    /**
     * JUnit test entry point. Only runs when testEnv property is set to true.
     * @param userInput String input from the user
     * @return String output displayed to the user
     */
    public String testDuke(String userInput) {
        assert this.testEnv : "testDuke method should only used for testing. ";
        ParserOutput parserOutput = null;
        try {
            parserOutput = Parser.parse(userInput);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return "";
        }

        if (parserOutput.isBye()) {
            return Ui.printGoodbye();
        }

        return this.readParseGui(parserOutput);
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
        Button sendButton = new Button("Send");

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

        sendHelloMessage();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogueContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void sendHelloMessage() {
        Font font = Font.font("Source Sans Pro Semibold", FontWeight.NORMAL, FontPosture.REGULAR, 16);
        Label dukeText = getDialogLabel(Ui.printHello(), font, Paint.valueOf("cb4b16"));
        dialogueContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    private Label getDialogLabel(String text, Font font, Paint fill) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setTextFill(fill);
        textToAdd.setFont(font);
        textToAdd.setPadding(new Insets(10, 10, 10, 10));

        return textToAdd;
    }

    private void handleUserInput() {
        Font userFont = Font.font("ubuntu", FontWeight.NORMAL, FontPosture.REGULAR, 16);
        Font font = Font.font("Source Sans Pro Semibold", FontWeight.NORMAL, FontPosture.REGULAR, 16);

        Label userText = getDialogLabel(userInput.getText(), userFont, Paint.valueOf("141823"));
        Label dukeText = getDialogLabel(getResponse(userInput.getText()), font, Paint.valueOf("cb4b16"));

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
        System.out.println(new Duke().dukeRunner());
    }
}
