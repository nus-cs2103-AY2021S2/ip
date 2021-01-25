package chatbot;

import chatbot.commands.ChatBotCommand;
import chatbot.exceptions.ChatBotException;

public class ChatBot {
    private Storage storage;
    private TaskHandler th;
    private Ui ui;

    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        th = new TaskHandler();
        try {
            th.loadTaskList(storage.loadTaskList());
        } catch (ChatBotException e) {
            th.clearTaskList();;
            ui.errorLine(e.getMessage());
        }
    }

    private void run() {
        ui.greeting();

        boolean isExit = false;
        while (!isExit) {

            try {
                String command = ui.nextCommand();
                ui.linkBreaker();
                ChatBotCommand c = Parser.parse(command);
                c.runTask(ui, th, storage);
                isExit = c.isTerminated();

            } catch (ChatBotException e) {
                ui.errorLine(e.getMessage());
            } finally {
                ui.linkBreaker();
            }

        }
    }
    public static void main(String[] args) {
        new ChatBot("./data/taskData.txt").run();
    }
}
