import commands.Command;
import commands.CommandResponse;
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

    /**
     * Returns {@code CommandResponse} with the response message and whether to exit after command
     *
     * @param userInput String of user input
     * @return          CommandResponse
     */
    public CommandResponse getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            CommandResponse response = command.execute(taskList, snomio, storage);
            return response;
        } catch (SnomException e) {
            return new CommandResponse(e.getMessage(), false);
        }
    }

}
