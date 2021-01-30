package yoda;

import yoda.task.TaskList;
import yoda.storage.Storage;
import yoda.ui.Ui;
import yoda.command.Command;
import yoda.parser.Parser;

public class Yoda {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Yoda(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = storage.load();
    }

    public void runYoda() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            ui.showLine();
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            ui.showLine();
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home+"/dukeTasks.txt";
        Yoda duke = new Yoda(filePath);
        duke.runYoda();
    }
}
