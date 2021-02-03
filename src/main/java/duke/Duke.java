package duke;

/**
 * Duke is a chatbot that helps the user to keep track of tasks that needs to be done.
 */

public class Duke {

    public static void main(String[] args) throws DukeException, Exception {
        Ui.greet();
        Parser.read();
    }
}
