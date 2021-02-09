package duke;

import duke.system.Parser;
import duke.system.Storage;
import duke.system.view.Gui;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    private TaskList tasks;
    private Parser in;

    public Duke() {
        in = new Parser();
        tasks = new TaskList();
    }

    public String getResponse(String input, TaskList tasks) {
        in = new Parser(input);
        return in.print(tasks);
    }

    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
