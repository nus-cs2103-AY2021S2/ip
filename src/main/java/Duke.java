/**
 * Task List manager that can store task lists.
 */
public class Duke {

    /** String indicating location of task list file */
    static private String STORAGE_LOCATION = "dukeTaskList.txt";

    /** Ui to display messages for user */
    private Ui ui;

    /**
     * Creates a new Duke object that contains a list of tasks from file at given path.
     * If no file is found, an empty task list is created instead.
     */
    public Duke() {
        ui = new Ui();
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
            Command c = parser.getCommand(parser, ui, STORAGE_LOCATION);
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

