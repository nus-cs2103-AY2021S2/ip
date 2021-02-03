import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Oracle {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Oracle(String filePath) {
        ui = new Ui();
        parser = new Parser(ui);
        // storage holds the arraylist used to store all the tasks given to THE ORACLE
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        // SCANNER takes input from user in a while loop, parses input using a series of if-else statements
        while (true) {
            String input = ui.readCommand();
            Command c = parser.parse(input);
            if (!c.execute(ui, tasks)){
                break;
            }
        }
        storage.store(tasks.getTasks());
    }

    public static void main(String[] args) {
        new Oracle("./oracle_data.txt").run();
    }
}
