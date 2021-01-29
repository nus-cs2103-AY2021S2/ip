import commands.Command;
import exceptions.SnomException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Snomio;

/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author: Sharptail
 */
public class Snom {
    private Storage storage;
    private TaskList taskList;
    private Snomio snomio;

    public Snom(String dirPath, String filePath){
        snomio = new Snomio(System.in, System.out);
        storage = new Storage(dirPath, filePath);
        try{
            taskList = new TaskList(storage.readFile());
        }catch(SnomException e){
            snomio.showLoadingError();
        }
    }

    public void run(){
        snomio.showWelcomeMsg();
        boolean isExit = false;
        while(!isExit){
            try {
                String commandStr = snomio.readWord();
                Command command = Parser.parse(commandStr);
                command.execute(taskList, snomio, storage);
                isExit = command.isExit();
            } catch (SnomException e) {
                snomio.showError(e.getMessage());
            }
        }
        snomio.close();
    }

    public static void main(String[] args) {
        new Snom("data", "snom.txt").run();
    }
}
