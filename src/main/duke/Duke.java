package main.duke;

import main.duke.io.Storage;
import main.duke.io.Parser;
import main.duke.DukeList;
import main.duke.command.ICommand;
public class Duke {
    private Storage storage;
    private DukeList dukeList;
    private Ui ui;

    /**
     * Constructor for duke
     * @param filePath contains file where the task list is being saved
     */
    public Duke(String filePath){
        ui = new  Ui();
        storage = new Storage(filePath);
        dukeList = storage.load();
    }

    public String getReply (String input){
        Parser parser = new Parser();
        try{
            ICommand command = parser.parse(input);
            String reply = command.run(ui,dukeList, storage);
            storage.writeFile();
            assert(reply!=null);
            return reply;
        }
        catch (DukeException e)
        {
            return e.getMessage();
        }
    }
}
