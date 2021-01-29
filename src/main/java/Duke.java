public class Duke {
    private final Ui ui;
    private final Storage fio;

    public Duke() {
        this.ui = new Ui();
        this.fio = new Storage();

    }

    public void run() {
        this.ui.showIntro();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ParseCommands parseCommands = ParseCommands.parseLine(fullCommand, this.fio.getArrSize());
                parseCommands.executeCommand(ui, this.fio);
                isExit = parseCommands.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
        }
        fio.beginClose();
        fio.closeFile();
    }

    public static void main(String[] args) {

        new Duke().run();
    }
}
