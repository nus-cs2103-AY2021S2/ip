import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(storage.loadFile());
            this.ui.loadingSuccess();
        }
        catch (DukeException e) {
            this.ui.showError(e.getMessage());
            this.taskList = new TaskList();
            this.ui.loadingFailure();
        }
    }


    public void run() {
        this.ui.welcomeMsg();
        Parser exec = new Parser(taskList);
        Scanner sc = new Scanner(System.in);
        String command;

        while (sc.hasNext()) {
            command = sc.nextLine();
            try {
                exec.executeCommand(command);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            if (!exec.isAlive) {
                try {
                    this.storage.saveFile(this.taskList.list);
                    ui.byeMsg();
                    sc.close();
                    break;
                }
                catch (DukeException e) {
                    ui.showError(e.getMessage());
                    break;
                }

            }

        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
