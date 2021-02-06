import alice.Alice;
import alice.command.Parser;
import alice.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;

import static javafx.application.Application.launch;

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

	private Alice alice;

	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
	private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
	}

	private static String getDataPath() {
		if (Storage.OS.contains("win")) {
			return String.join(File.separator, System.getenv("USERPROFILE"),
					".alice_save", "data", "save_data");
		} else {
			return String.join(File.separator, "~", ".alice_save", "data", "save_data");
		}
	}

	private static TaskList loadTasks() {
		byte[] data = Storage.loadBytes(getDataPath());
		if (data == null) {
			return new TaskList(new ArrayList<>());
		} else {
			return Storage.deserialize(data, TaskList.class);
		}
	}

	private static boolean saveTasks(TaskList taskList) {
		byte[] data = Storage.serialize(taskList);
		return Storage.saveBytes(getDataPath(), data);
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		TaskList tasks = loadTasks();
		if (this.alice == null) {
			alice = new Alice(loadTasks(), false);
		}
		alice = Parser.parse(input.trim()).execute(alice);
		String response = alice.getCurrentMessage();
		if (alice.hasDelta() && !saveTasks(alice.getData())) {
			System.out.println("Error saving tasks to " + getDataPath());
		}
		if (alice.getIsDone()) {
			System.exit(0);
		}
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog(input, userImage),
				DialogBox.getDukeDialog(response, dukeImage)
		);
		userInput.clear();
	}
}