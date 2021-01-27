package owen;

/**
 * Starts Owen chatbot with a terminal UI interface.
 */
public class Main {
    /**
     * Main entry point for Owen chatbot with terminal UI interface.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
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
