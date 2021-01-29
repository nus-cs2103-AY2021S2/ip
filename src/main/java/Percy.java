import java.io.IOException;

import percy.command.Command;
import percy.command.Parser;
import percy.exception.PercyException;
import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;


public class Percy {
    private TaskList list;
    private Ui ui;
    private Storage storage;
    private String filePath = "data/percy.txt";

    /**
     * Constructs the chat bot Percy.
     */
    public Percy() throws IOException {
        this.list = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
    }

    /**
     * Runs the chat bot which accepts commands and replies accordingly.
     *
     * @throws IOException
     */
    public void run() throws IOException {
        list.updateTaskList(storage.load());
        Ui.printStartUpMsg();
        while (true) {
            String command = Ui.readCommand();
            Parser parser = new Parser(command);
            try {
                Command cmd = parser.getCommand();
                String response = cmd.execute(list, storage);
                System.out.println(response);
                if (cmd.isExit()) {
                    break;
                }
            } catch (IOException e) {
                break;
            } catch (PercyException e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Runs the chat bot Percy.
     */
    public static void main(String [] args) {
        try {
            new Percy().run();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
