package com.tjtanjin.steve;

import com.tjtanjin.steve.commands.CommandHandler;
import com.tjtanjin.steve.parser.Parser;
import com.tjtanjin.steve.storage.StorageHandler;
import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Steve class creates and allocates all key objects.
 */
public class Steve {

    private final Parser parser;

    /**
     * Constructor for Steve which stores initializes key classes and stores parser to be used by UiHandler.
     */
    public Steve() {
        StorageHandler storageHandler = new StorageHandler("./data/tasks.json");
        TaskHandler taskHandler = new TaskHandler(storageHandler);
        CommandHandler commandHandler = new CommandHandler(taskHandler);
        this.parser = new Parser(commandHandler);
    }

    /**
     * Gets the parser.
     * @return parser
     */
    public Parser getParser() {
        return this.parser;
    }
}
