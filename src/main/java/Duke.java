public class Duke {
    private boolean isSetUp = false;
    private Ui ui;

    private void setUp() {
        isSetUp = true;
        ui = new Ui();
    }

    public String getResponse(String fullCommand) {
        if (!isSetUp) {
            setUp();
            return ui.load(fullCommand);
        }
        return ui.parseAndPrint(fullCommand);
    }
}
