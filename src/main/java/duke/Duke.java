package duke;

/**
 * Duke is a chatbot that helps the user to keep track of tasks that needs to be done.
 */

public class Duke {

    /**
     * main method run
     * @param args
     * @throws DukeException
     * @throws Exception
     */
    public static void main(String[] args) throws DukeException, Exception {
        Ui.greet();
        Parser.read();
    }
}
