package duke;

import java.util.Scanner;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Creates a new instance of 'Duke' bot
     * @param filePath path to file containing saved tasks
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load(), this.storage);
        this.parser = new Parser();
        this.ui = new Ui("Olly");
    }

    /**
     * Spins up the bot and allow execution from users
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.speak("Hey! Welcome to the chatbot. What can I do for you today?");

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command c = this.parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException dukeEx) {
                dukeEx.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/task.txt").run();
    }
}
