import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws FileNotFoundException {
        ui.showGreetings();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Parser parser = new Parser(storage, ui, tasks);

        while (!Parser.hasExited(command)) {
            parser.parseCommand(command);
            command = scanner.nextLine();
        }

        if (Parser.hasExited(command)) {
            ui.showExit();
            storage.save(tasks.list);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("duke.txt").run();
    }

}
