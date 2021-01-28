package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            taskList.load(storage);
            ui.showLoadSuccess();
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCmd = ui.readCommand();
                Command parsedCmd = Parser.parse(fullCmd);
                parsedCmd.execute(storage, ui, taskList);
                isExit = parsedCmd.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
