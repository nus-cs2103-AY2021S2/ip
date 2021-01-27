package duke;

import duke.commands.ChatBotCommand;
import duke.exceptions.ChatBotException;

public class Duke {
    private Storage storage;
    private TaskHandler th;
    private Ui ui;

    /**
     *  ChatBot constructor.
     *
     *  @param filePath Relative filepath to persistent storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        th = new TaskHandler();
        try {
            th.loadTaskList(storage.loadTaskList());
        } catch (ChatBotException e) {
            th.clearTaskList();;
            ui.errorLine(e.getMessage());
        }
    }

    /**
     * Runs the ChatBot Program.
     */
    private void run() {
        ui.greeting();

        boolean isExit = false;
        while (!isExit) {

            try {
                String command = ui.nextCommand();
                ui.linkBreaker();
                ChatBotCommand c = Parser.parse(command);
                c.runTask(ui, th, storage);
                isExit = c.isTerminated();

            } catch (ChatBotException e) {
                ui.errorLine(e.getMessage());
            } finally {
                ui.linkBreaker();
            }

        }
    }
    public static void main(String[] args) {
        new Duke("./data/taskData.txt").run();
    }
}
