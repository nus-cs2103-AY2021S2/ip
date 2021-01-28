package duke;

public class Duke {
    private TaskList task;
    private Ui ui;
    private FileSaver fs;

    public Duke (String folderName, String fileName) {
        fs = new FileSaver(folderName, fileName);
        ui = new Ui();
        task = new TaskList();
    }
    public void run() {
        ui.greeting();
        try {
            fs.load(task);
        } catch (Exception e) {
            //TODO: handle exception
            ui.printErrorMessage(e.getMessage());
        }
        ui.runUi(task, fs);
        ui.close();
    }
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}
