import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final TaskList taskLst;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        taskLst = new TaskList();

        // Terminate if unable to initialise storage
        try {
            storage = new Storage();
            storage.fillTaskLst(taskLst);
        } catch (IOException e) {
            ui.print(String.format("Unable to initialise Storage: %s", e));
            System.exit(1);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        ui.printWelcomeMsg();

        while (!isExit) {
            try {
                String input = sc.nextLine();
                Command cmd = Parser.parse(input);

                String resp = cmd.execute(taskLst);
                isExit = cmd.isExit();
                storage.saveTaskLst(taskLst);

                ui.print(resp);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
