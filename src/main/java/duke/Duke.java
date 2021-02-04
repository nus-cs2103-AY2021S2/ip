package duke;

import java.io.IOException;
import java.text.ParseException;

public class Duke {
    public Parser parser;

    public Duke() throws IOException {
        this.parser = new Parser();
    }

    /*
     * Runs the program.
     */
    public void run() throws IOException, ParseException {
        parser.storage.importTasks();
        parser.UI.logo();
        parser.UI.greet();
        while(true){
            parser.process();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Duke duke = new Duke();
        duke.run();
    }
}
