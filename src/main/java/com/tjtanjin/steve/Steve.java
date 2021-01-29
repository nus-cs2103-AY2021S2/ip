package com.tjtanjin.steve;

import com.tjtanjin.steve.commands.CommandHandler;
import com.tjtanjin.steve.parser.Parser;
import com.tjtanjin.steve.storage.StorageHandler;
import com.tjtanjin.steve.tasks.TaskHandler;
import com.tjtanjin.steve.ui.UiHandler;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Steve is a project that eventually builds into a personal assistant chat bot.
 */
public class Steve extends Application {

    /**
     * Initializes key classes and calls the UiHandler to show the stage.
     *
     * @param stage primary stage provided by javafx
     */
    @Override
    public void start(Stage stage) {
        /*
         * initialize ui handler, storage handler, task handler, command handler and parser
         * then set them up to interact with each other and the user as below:
         * User <-> UiHandler <-> Parser <-> CommandHandler <-> TaskHandler <-> StorageHandler
         */
        StorageHandler storageHandler = new StorageHandler("./data/tasks.json");
        TaskHandler taskHandler = new TaskHandler(storageHandler);
        CommandHandler commandHandler = new CommandHandler(taskHandler);
        Parser parser = new Parser(commandHandler);
        UiHandler uiHandler = new UiHandler(parser);
        uiHandler.showMainScreen(stage);
        uiHandler.showWelcome();
    }
}
