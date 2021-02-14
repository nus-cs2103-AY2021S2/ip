package soonwee.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox dialogContainer;
	@FXML
	private TextField userInput;
	@FXML
	private Button sendButton;

	private Duke dukeBot;

	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
	private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	/**
	 * Initializes the main window and greets the user.
	 */
	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

		dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(printGreeting(), dukeImage));
	}

	/**
	 * Sets the instance of a Duke bot.
	 *
	 * @param duke the Duke bot instance
	 */
	public void setDuke(Duke duke) {
		dukeBot = duke;
	}

	/**
	 * Prints greeting message.
	 *
	 * @return greeting message
	 */
	public String printGreeting() {
		String text = new String();
		String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
		text = text + printLine() + "\n" + greet + "\n" + printLine();
		return text;
	}

	/**
	 * Prints line.
	 *
	 * @return prined lines
	 */
	public String printLine() {
		return "---------------------------";
	}

	/**
	 * Prints bye message.
	 *
	 * @return bye message
	 */
	public String printBye() {
		String text = "Bye. Hope to see you again soon!\n";
		return text;
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() throws IOException {
		String input = userInput.getText();
		if (input.equals("bye")) {
			dialogContainer.getChildren().addAll(
					DialogBox.getUserDialog(input, userImage),
					DialogBox.getDukeDialog(printBye(), dukeImage)
			);
		} else {
			String response = dukeBot.getResponse(input);
			dialogContainer.getChildren().addAll(
					DialogBox.getUserDialog(input, userImage),
					DialogBox.getDukeDialog(response, dukeImage)
			);
		}
		userInput.clear();
	}
}