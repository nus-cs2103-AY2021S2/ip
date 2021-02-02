package main.java;


import main.java.classes.*;
import main.java.command.*;

import java.io.IOException;

public class Duckie {
    private Storage storage;
    private TaskList lst;
    private Ui ui;

    public Duckie(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            lst = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            //ui.showLoadingError();
            lst = new TaskList();
        }
    }

    public void run() throws IOException {
        storage.loadTasks();
        ui.startMessage();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String fullInput = ui.readInput();
                ui.customLine();
                Command c = Parser.parse(fullInput);
                c.execute(lst, ui, storage);
                isEnd = c.isEnd();
            } catch (DuckieException e) {
                e.printStackTrace();
            }
        }
        storage.saveTasks(lst.getTaskList());
        ui.endMessage();
    }

    public static void main(String[] args) throws IOException, DuckieException {
        new Duckie("src/duckie.txt").run();
    }
}