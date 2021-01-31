import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        /*try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }*/
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.reply();
        Parser parser = new Parser(tasks, ui);
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
        new Duke("C:/ip/src/main/java/Duke.java").run();
    }
}