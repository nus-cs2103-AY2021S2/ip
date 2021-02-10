package owen;

/**
 * Main entrypoint for Owen chatbot with GUI interface.
 */
public class Main {
    public static void main(String[] args) {
        startGui(args);
    }

    /**
     * Starts GUI interface for interacting with Owen chatbot.
     *
     * @param args Program arguments.
     */
    public static void startGui(String[] args) {
        Gui.main(args);
    }

    /**
     * Starts Terminal interface for interacting with Owen chatbot.
     *
     * @param args Program arguments.
     */
    public static void startTerminal(String[] args) {
        Chatbot owen = new Owen();
        Ui ui = new Ui();

        String response = owen.getResponse();
        ui.outputResponse(response);

        while (owen.isRunning()) {
            String command = ui.getInput();
            owen = owen.parseCommand(command);
            response = owen.getResponse();
            ui.outputResponse(response);
        }
    }
}
