import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.reply();
        Parser parser = new Parser(tasks, ui);
        while (true) {
            String command = sc.nextLine();
            parser.insertCommand(command);
            parser.process();
            if (parser.isEnd()) {
                break;

            }
        }
        System.exit(0);
    }

    public static void main(String[] args) throws IOException{
        new Duke("data/DukeData.txt").run();
    }
}