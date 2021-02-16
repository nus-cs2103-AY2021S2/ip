/**
 * Task List manager that can store task lists.
 */
public class Duke {

    /** Storage to handle task list files */
    private Storage storage;

    /** Ui to display messages for user */
    private Ui ui;

    /**
     * Creates a new Duke object that contains a list of tasks from file at given path.
     * If no file is found, an empty task list is created instead.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("dukeTaskList.txt");
    }

    /**
     * Runs the program.
     *
     * @throws DukeException If user input is incorrect.
     */
    public String run(String input) {

        String output;

        try {
            Parser parser = new Parser(input);
            Command c = parser.getCommand(parser, ui, storage);
            output = c.execute();

        } catch (DukeException e) {
            output = e.errorMessage();
        }

        return output;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return run(input);
    }

}

