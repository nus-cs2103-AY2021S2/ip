public class Duke {
    private boolean isSetUp = false;

    public String getResponse(String fullCommand) {
        if (!isSetUp) {
            isSetUp = true;
            return Ui.load(fullCommand);
        }
        return Ui.parseAndPrint(fullCommand);
    }
}
