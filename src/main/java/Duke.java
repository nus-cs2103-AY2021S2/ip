import Duke.*;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final Chatbot chatbot;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        chatbot = new Chatbot(storage.readFile());
    }

    public static void main(String[] args) {
        new Duke("./data.txt").execute();
    }

    public void execute() {
        ui.welcome();
        chatbot.execute();
        ui.welfare();
        storage.updateFile(chatbot.getTaskList());
    }

}
