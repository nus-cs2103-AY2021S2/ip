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

        Storage storage = new Storage("./data/tasks.json");
        TaskList taskList = new TaskList(storage);
        Parser parser = new Parser(taskList);
        Ui.showWelcome();
        listenInput(parser);
    }

    /**
     * Listens for input from the user.
     * @param parser class that handles parsing of input
     */
    private static void listenInput(Parser parser) {
        Scanner sc = new Scanner(System.in);
        Ui.showDivider();

        while (sc.hasNextLine()) {
            Ui.showDivider();

            String input = Ui.readCommand(sc);

            parser.parseInput(input);

            Ui.showDivider();
        }
    }
}
