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
        greet();
    }

    public String executeCommand(Command command) throws DukeException {
        return command.execute(this.list);
    }

    private void greet() {
        Ui.printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }


    /**
     *  Provides responses to commands that are passed in by the user.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.handleInput(input);
            return this.executeCommand(command);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
