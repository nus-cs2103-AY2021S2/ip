public class Duke {
    private boolean isSetUp = false;
    private Ui ui;

    public String getResponse(String fullCommand) {
        if (!isSetUp) {
            isSetUp = true;
            ui = new Ui();
            return ui.load(fullCommand);
        }
        return ui.parseAndPrint(fullCommand);
    }
}
