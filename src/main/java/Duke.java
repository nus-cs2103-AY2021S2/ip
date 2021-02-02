import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(this.storage.loadtasks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        duke.chat(scanner, duke.tasks);
    }

    public void chat(Scanner scanner, TaskList tasks) throws IOException {
        this.ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parse(input);
                command.execute(tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (Exception e) {
                this.ui.showError(e.getMessage());
            }
        }
    }
}
