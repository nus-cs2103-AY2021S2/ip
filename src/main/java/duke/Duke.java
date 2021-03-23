package duke;

import duke.command.Command;
import javafx.util.Pair;

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

    /**
     * Greets the user
     * @return greeting message.
     */
    public String greet() {
        return "Hello, welcome to Duke";
    }

    /**
     * Executes the given command.
     * @param command command to be executed.
     * @return the pair containing the string response as well as a CallbackFunction that can be run.
     * @throws DukeException if there is an error executing the command.
     */
    public Pair<String, CallbackFunction> executeCommand(Command command) throws DukeException {
        return command.execute(this.list);
    }

    /**
     * Provides responses to commands that are passed in by the user.
     * @param input the user input.
     * @return response that duke has, corresponding to the user input as well as a CallbackFunction that can be run.
     */
    public Pair<String, CallbackFunction> getResponse(String input) {
        Command command;
        try {
            command = Parser.handleInput(input);
            Pair<String, CallbackFunction> response = this.executeCommand(command);
            assert response != null && response.getKey().length() > 0 : "Duke must always respond with something";
            return response;
        } catch (DukeException e) {
            return new Pair<>(e.getMessage(), CallbackFunction.empty());
        }
    }

}
