package com.lirc572.ip;

import java.util.Scanner;

/**
 * Todo:
 * - exceptions yet to be handled:
 * - number of tasks > 100
 * - multiple spaces in between tokens
 * - done command
 * - w/o number
 * - number out of range
 * - help command
 */

public class Duke {

    /**
     * The task list
     */
    private final TaskList tasks;

    public Duke() {
        this.tasks = new TaskList();
        Storage.readFromFile(this.tasks);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        Ui.printLogo();
        Ui.printEmptyLine();
        Ui.printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        Ui.printLine("Sou, watashi desu!");
        Ui.printHorizontalLine();
        Ui.printEmptyLine();
        for (; ; ) {
            try {
                if (!Parser.processCommand(sc.nextLine(), this.tasks)) {
                    break;
                }
            } catch (Exception e) {
                Ui.printError(e);
                Ui.printHorizontalLine();
                Ui.printEmptyLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
