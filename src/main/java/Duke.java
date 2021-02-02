import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/*
 * The main class for the Duke app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    /*
     * Run the Duke app.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.reply();
        Path path = Paths.get(this.storage.getFilePath());
        if (Files.exists(path)) {
            this.tasks = storage.readFromFile();
        } else {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Parser parser = new Parser(tasks, ui, storage);
        while (true) {
            String command = sc.nextLine();
            parser.insertCommand(command);
            parser.process();
            if (parser.isFinished()) {
                break;
            }
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}