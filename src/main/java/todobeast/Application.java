package todobeast;

import todobeast.commands.Command;
import todobeast.exceptions.ToDoBeastException;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    private static final String DATA_DIRECTORY_NAME = "./data/";
    private static final String DATA_FILE_NAME = "data.txt";

    public Application() {
        storage = new Storage(DATA_DIRECTORY_NAME, DATA_FILE_NAME);
        ui = new Ui();
        try {
            ui.showLoading();
            taskList = new TaskList(storage.loadTasks());
            ui.showLoadingSuccess();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void runApplication() {
        ui.showWelcome();
        ui.showInstructions();
        ui.showLine();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (ToDoBeastException e) {
                ui.showError(e.getMessage());
            } finally {
                try {
                    storage.saveToStorage(taskList.formatTaskListForStorage());
                } catch (IOException e) {
                    ui.showError(e.getMessage());
                }
                ui.showLine();
            }
        }

    }

}
