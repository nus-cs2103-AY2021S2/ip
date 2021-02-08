package duke;

import duke.command.Command;

public class Duke {
    /** Stores list of tasks */
    private TaskList list;

    /**
     * Initializes a Duke object with an empty TaskList.
     */
    public Duke() {
        try {
            this.list = new TaskList();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    public String executeCommand(Command command) throws DukeException {
        return command.execute(this.list);
    }

    /**
     * Provides responses to commands that are passed in by the user.
     * @param input the user input.
     * @return response that duke has, corresponding to the user input.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.handleInput(input);
            String response = this.executeCommand(command);
            assert response != null && response.length() > 0 : "Duke must always respond with something";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
