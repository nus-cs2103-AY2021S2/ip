package com.nus.duke;

import com.nus.duke.command.Command;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.data.*;
import com.nus.duke.parser.CommandParser;
import com.nus.duke.ui.TextUi;

public class Duke {

    private TextUi ui;
    private TaskList taskList;
    private CommandParser parser;

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
        // TODO: Read/write to file on disk
        this.taskList = new TaskList();
        this.parser = new CommandParser();
    }

    private void mainLoop() {
        Command command;
        do {
            String userInput = this.ui.readUserCommand();
            command = this.parser.parseCommand(userInput);
            command.setContext(this.taskList);
            String result = command.execute();
            this.ui.printToUser(result);
        } while (!(command instanceof ExitCommand));
    }

    private void exit() {
        this.ui.printToUser("Bye. See you again.");
    }
}
