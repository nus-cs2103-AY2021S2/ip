import java.io.IOException;

import javafx.application.Application;
// import percy.command.Command;
// import percy.command.Parser;
// import percy.exception.PercyException;
import percy.storage.Storage;
import percy.task.TaskList;
import percy.ui.Ui;


public class Percy {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private String filePath;

    /**
     * Constructs the chat bot Percy.
     */
    public Percy(String filePath) throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.filePath = filePath;
    }

    /**
     * Runs the chat bot which accepts commands and replies accordingly.
     *
     * @throws IOException
     */
    /*
    public void run() throws IOException {
        tasks.updateTaskList(storage.load());
        Ui.printStartUpMsg();
        while (true) {
            String command = Ui.readCommand();
            Parser parser = new Parser(command);
            try {
                Command cmd = parser.getCommand();
                String response = cmd.execute(tasks, storage);
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
    */


    public Storage getStorage() {
        return storage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Runs the chat bot Percy.
     */
    public static void main(String[] args) throws IOException {
        Application.launch(Gui.class, args);
        /* // Command-Line mode
            new Percy().run();
        } catch (IOException ex) {
            System.err.println(ex);
        }
         */
    }
}
