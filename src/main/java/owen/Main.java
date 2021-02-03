package owen;

public class Main {
    public static void main(String[] args) {
        startGui(args);
    }

    public static void startGui(String[] args) {
        Gui.main(args);
    }

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
