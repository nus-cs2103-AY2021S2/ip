import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Duke allows the user to maintain a list of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /*public void run() {
        while (!this.ui.shouldExit() && this.ui.hasNext()) {
            this.tasks = ui.process(this.tasks);
            try {
                FileWriter fw = new FileWriter(this.filePath);
                for (Task t : this.tasks.getList()) {
                    String rmb = "";
                    if (t instanceof Event) {
                        rmb += "E";
                    } else if (t instanceof Deadline) {
                        rmb += "D";
                    } else if (t instanceof Todo) {
                        rmb += "T";
                    }
                    rmb += " | ";
                    if (t.isCompleted()) {
                        rmb += "1";
                    } else {
                        rmb += "0";
                    }
                    rmb += " | ";
                    rmb += t.getDescription();
                    if (t instanceof DueDate) {
                        rmb += " | ";
                        rmb += ((DueDate) t).getDueDate();
                    }
                    fw.write(rmb + "\n");
                }
                fw.close();
            } catch (IOException e) {
            }
        }
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        Parser parser = new Parser();
        parser = parser.parse(input);
        String message = this.ui.getOutput(this.tasks, parser);
        this.tasks = this.ui.process(this.tasks, parser);
        return "Duke heard: " + message;
    }

    protected String getGreeting() {
        return this.ui.greet();
    }
}
