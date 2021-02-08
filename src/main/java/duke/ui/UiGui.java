package duke.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UiGui extends Ui {

    private static final Font FONT = new Font("Consolas", 11);

    private final VBox dialogContainer;
    private final TextField textField;

    /**
     * Creates reader from dialog box
     */
    public UiGui(TextField textField, VBox dialogContainer) {
        this.textField = textField;
        this.dialogContainer = dialogContainer;
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setFont(FONT);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    @Override
    public boolean getUserConfirmation(String confirmationPrompt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationPrompt);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }

    @Override
    public String getUserInput(String inputPrompt, String inputHint) {
        String text = textField.getText();
        textField.clear();
        return text;
    }

    @Override
    public String getUserInputSecondary(String inputPrompt, String inputHint) {
        TextInputDialog dialog = new TextInputDialog(inputHint);
        dialog.setHeaderText(inputHint);
        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    /**
     * Send exception for pretty printing.
     *
     * Exception message can be composed of multiple lines.
     *
     * @param e Exception.
     */
    @Override
    public void printError(DukeException e) {
        String message = String.valueOf(e).strip();
        if (message.contains("\n")) {
            int index = message.indexOf('\n');
            String firstLine = message.substring(0, index);
            String[] restLines = message.substring(index + 1).split("\n");
            List<String> lines = Arrays.stream(restLines).map(s -> "  " + s).collect(Collectors.toList());
            showResponse("☹ OOPS!!! " + firstLine, lines);
        } else {
            showResponse("☹ OOPS!!! " + message);
        }
    }

    /**
     * Prints user input as callback
     *
     * @param callbackText string
     */
    @Override
    public void printCallback(String callbackText) {
        dialogContainer.getChildren().add(getDialogLabel(callbackText));
    }

    /**
     * Prints user input as callback
     *
     * @param responseText string
     */
    @Override
    public void printResponse(String responseText) {
        dialogContainer.getChildren().add(getDialogLabel(responseText));
    }
}
