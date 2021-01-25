package duck;

import duck.operation.Command;
import duck.operation.Parser;
import duck.operation.Storage;
import duck.operation.Ui;
import duck.task.TaskList;

import java.io.*;

public class Duke {
    //private  String filePathOfData = ".\\data\\duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();
    }

    public void run() throws IOException {
        ui.showWelcome();
        String fullCommand = "";
        do {
            fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);

        } while (!fullCommand.equals("bye"));
    }

    public static void main(String[] args) throws IOException {
        String filePathOfData = ".\\data\\duke.txt";
        new Duke(filePathOfData).run();
    }
}
