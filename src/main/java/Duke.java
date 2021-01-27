
import java.io.IOException;


public class Duke {

    static Storage storage = new Storage(); 

    static TaskList list = new TaskList();

    static Parser parser = new Parser();

    public static void main(String[] args) throws Exception {
        if(storage.isSavedHistory()) {
            storage.initialise(list);
        }
        //Parser
        parser.readInput(list);
        
        //Storage
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    /**
     * Function to call when we want to end main, to store the TaskList into storage
     * 
     * @throws IOException
     */
    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}



