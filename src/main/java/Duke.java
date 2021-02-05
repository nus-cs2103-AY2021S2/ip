import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BaseException e) {
            ui.showError(e.toString());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommmand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommmand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BaseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("src/data.txt").run();
        /*
        Ui ui = new Ui();
        Storage storage = new Storage()
        //ui.showLoadingError();
        TaskList tasks = new TaskList();
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
                try {
                    String readInputLine = ui.readCommand();
                    ui.showLine();
                    Command c = Parser.parse(readInputLine);
                    c.execute(tasks, ui);
                    isExit = c.isExit();
                } catch (BaseException e) {
                    ui.showError(e.getMessage());
                } finally {
                    ui.showLine();
                }
        }
      */

    }
}
