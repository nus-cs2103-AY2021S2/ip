import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | EmptyArgumentException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws EmptyArgumentException, BadDateException, InvalidCommandException, IOException {
        ui.sendWelcomeMessage();
        while(true) {
            String line = ui.listenToInput();
            Command command = parser.parseCommand(line);
            if(command.isExit()) break;
            tasks.run(command,ui,storage);
        }
        ui.byeUser();
    }

    public static void main(String[] args) throws EmptyArgumentException, BadDateException, InvalidCommandException, IOException {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
}