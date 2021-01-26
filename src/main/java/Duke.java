import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke{
    private Storage storage;
    private TaskManager taskManager;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskManager = new TaskManager();
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.parseToStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            ui.showError("Storage file not found!");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Internal error! Storage file not in the correct format.");
            }
        }
    }

    public static void main(String[] args) {
        /*TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("./src/main/data/Data.txt");
        Ui ui = new Ui();
        ui.showWelcome();
        // Loading file from hard disk
        try {
            ArrayList<String> oldData = storage.load();
            ArrayList<String> parsedData = Parser.parseToStart(oldData);
            taskManager.upload(parsedData);
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found!");
        }
        //Taking in current session's tasks and other commands
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                command.execute(taskManager, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Storage file not in the correct format!");
            }
        }
        */
         new Duke("./src/main/data/Data.txt").run();

    }
}
