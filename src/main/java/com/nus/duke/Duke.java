package com.nus.duke;

import com.nus.duke.command.Command;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.common.DukeStorageException;
import com.nus.duke.data.TaskList;
import com.nus.duke.parser.CommandParser;
import com.nus.duke.storage.TaskListStorage;
import com.nus.duke.ui.TextUi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Duke extends Application {

    private TextUi ui;
    private TaskList taskList;
    private CommandParser parser;
    private TaskListStorage storage;

    public void run() {
        this.initializeDuke();
        this.ui.showWelcomeMessage();
        this.mainLoop();
        this.exit();
    }

    private void initializeDuke() {
        this.ui = new TextUi();
        this.storage = new TaskListStorage();
        this.parser = new CommandParser();
        try {
            this.taskList = this.storage.load();
        } catch (DukeStorageException e) {
            this.ui.printToUser(e.getLocalizedMessage());
            this.taskList = new TaskList();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label(
                "Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
        new Duke().run();
    }

    private void mainLoop() {
        Command command;
        do {
            String userInput = this.ui.readUserCommand();
            command = this.parser.parseCommand(userInput);
            command.setContext(this.taskList);
            String result = command.execute();
            try {
                this.storage.save(this.taskList);
            } catch (DukeStorageException e) {
                this.ui.printToUser(e.getLocalizedMessage());
            }
            this.ui.printToUser(result);
        } while (!(command instanceof ExitCommand));
    }

    private void exit() {
        this.ui.printToUser("Bye. See you again.");
    }
}
