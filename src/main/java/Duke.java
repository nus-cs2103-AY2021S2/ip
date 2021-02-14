import command.Command;
import javafx.application.Application;
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
import javafx.stage.Stage;
import nodes.DialogBox;
import task.TaskManager;
import util.DukeException;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private TaskManager taskManager;

    public Duke(Storage storage) {
        this.storage = storage;
        try {
            this.taskManager = storage.readTaskManager();
        } catch (IOException e) {
            this.taskManager = new TaskManager();
        }
    }

    public static void main(String[] args) {
        try {
            Storage storage = new Storage("data", "sweh.txt");
            Duke duke = new Duke(storage);
            Ui.printGreeting();
            duke.run();
        } catch (IOException e) {
            System.out.println("Unable to initialise storage for Sweh");
        }
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String message;
            boolean shouldQuit = false;
            try {
                Command c = Parser.parseCommand(userInput);
                c.execute(taskManager);
                message = c.getMessage();
                if (c.isQuitCommand()) {
                    shouldQuit = true;
                }
            } catch (DukeException e) {
                message = e.getMsg();
            }
            System.out.println(message);
            storage.writeTaskManager(taskManager);

            if (shouldQuit) {
                break;
            }
        }
    }

    public String respondToInput(String input) {
        try {
            Command c = Parser.parseCommand(input);
            c.execute(taskManager);
            storage.writeTaskManager(taskManager);
            return c.getMessage();
        } catch (DukeException e) {
            return e.getMsg();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
