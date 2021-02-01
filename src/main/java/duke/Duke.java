package duke;

import java.util.Scanner;

import java.io.IOException;

/**
 * An interactive chat bot made for CS2103 individual project.
 */
public class Duke {
    protected Ui ui;
    protected TaskList tasks;

    public Duke() {
        this.ui = new Ui(new Scanner(System.in));
        this.tasks = new TaskList();
    }

    /**
     * Runs the Duke chat bot program.
     */
    public void run() {
        // Initialise task list and user interface
        try {
            this.tasks.load();
        } catch (IOException ex) {
            System.out.println("  Unable to load tasks.");
        }
        this.ui.initialise(this.tasks);

        // Run chat bot
        this.ui.run();
    }

    /**
     * Starts the program.
     *
     * @param args User input.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
