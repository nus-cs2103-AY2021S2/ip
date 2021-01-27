import percy.exception.PercyException;
import percy.task.Deadline;
import percy.task.Event;
import percy.ui.UserInterface;

import java.io.IOException;

import percy.ui.UserInterface;
import percy.command.Command;
import percy.command.Parser;
import percy.task.TaskList;
import percy.storage.Storage;

public class Percy {
    TaskList list;
    UserInterface ui;
    Storage storage;
    String filePath = "data/percy.txt";

    public Percy() throws IOException {
        this.list = new TaskList();
        this.ui = new UserInterface();
        this.storage = new Storage(this.filePath);
    }

    public void run() throws IOException {
        list.updateTaskList(storage.load());
        UserInterface.printStartUpMsg();
        while (true) {
            String command = UserInterface.readCommand();
            Parser parser = new Parser(command);
            try {
                Command cmd = parser.getCommand();
                String response = "";
                response = cmd.execute(list, storage);
                System.out.println(response);
                if (cmd.isExit() == true) {
                    break;
                }
            } catch (IOException e) {
                break;
            } catch (PercyException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Percy().run();
        } catch(IOException ex) {
            System.err.println(ex);
        }
    }
}
