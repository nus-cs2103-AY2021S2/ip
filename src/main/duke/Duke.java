package duke;

import duke.io.Storage;
import duke.io.Parser;
import duke.command.ICommand;
public class Duke {
    private Storage storage;
    private DukeList DukeList;
    private Ui ui;


    public Duke(String filePath){
        ui = new  Ui();
        storage = new Storage(filePath);
        DukeList = storage.load();
    }

    public String getReply (String input){
        Parser parser = new Parser();
        try{
            ICommand command = parser.parse(input);
            String reply = command.run(ui,DukeList, storage);
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
