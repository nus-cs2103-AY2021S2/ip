package com.lirc572.ip;

import java.util.Scanner;

/**
 * The entry point of the program.
 */
public class Duke {

    /**
     * The task list.
     */
    private final TaskList tasks;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        this.tasks = new TaskList();
        Storage.readFromFile(this.tasks);
    }

    /**
     * Starts the REPL.
     */
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
        sc.close();
    }

    /**
     * Constructs a Duke object and run it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
