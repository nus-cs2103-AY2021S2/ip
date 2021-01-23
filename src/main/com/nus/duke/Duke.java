package com.nus.duke;

import com.nus.duke.command.Command;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.common.DukeStorageException;
import com.nus.duke.data.TaskList;
import com.nus.duke.parser.CommandParser;
import com.nus.duke.storage.TaskListStorage;
import com.nus.duke.ui.TextUi;

public class Duke {

    private TextUi ui;
    private TaskList taskList;
    private CommandParser parser;
    private TaskListStorage storage;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        this.init();
        this.ui.showWelcomeMessage();
        this.mainLoop();
        this.exit();
    }

    private void init() {
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
