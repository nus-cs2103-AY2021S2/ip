package com.tjtanjin.ip;

import java.util.Scanner;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {

    /**
     * Entry point of the program, first greets then listens for input from user.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        /*
         * initialize ui handler, storage handler, task handler, command handler and parser
         * then set them up to interact with each other and the user as below:
         * User <-> UiHandler <-> Parser <-> CommandHandler <-> TaskHandler <-> StorageHandler
         */
        UiHandler uiHandler = new UiHandler();
        StorageHandler storageHandler = new StorageHandler("./data/tasks.json");
        TaskHandler taskHandler = new TaskHandler(storageHandler);
        CommandHandler commandHandler = new CommandHandler(taskHandler);
        Parser parser = new Parser(commandHandler);
        listenInput(parser, uiHandler);
    }

    /**
     * Listens for input from the user.
     * @param parser class that handles parsing of input
     */
    private static void listenInput(Parser parser, UiHandler uiHandler) {
        Scanner sc = new Scanner(System.in);
        uiHandler.showWelcome();
        uiHandler.showDivider();

        while (sc.hasNextLine()) {
            uiHandler.showDivider();

            String input = UiHandler.readCommand(sc);

            uiHandler.showResponse(parser.parseInput(input));

            uiHandler.showDivider();
        }
    }
}
