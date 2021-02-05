package duke.processintructions;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Matches the command called by a user to the command return logic to be called in LogicHandler.
 */
public class CommandRouter {
    private boolean isExit;

    public CommandRouter() {
        isExit = false;
    }

    /**
     * Maps the string of the command name given in the user's input to the enumerated command handled by LogicHandler.
     * If no such command exists or there is an error, it will be mapped to the error command.
     *
     * @param command Command specified.
     * @param tasks TaskList object.
     * @param input Input of the user as a String.
     */
    public String route(Command command, TaskList tasks, String input) {
        LogicHandler logic = new LogicHandler();
        Ui ui = new Ui();

        switch (command) {
        case LIST:
            return logic.list(tasks.getList());
            // break intentionally omitted
        case DONE:
            return logic.done(input, tasks.getList());
            // break intentionally omitted
        case TODO:
            return logic.todo(input, tasks.getList());
            // break intentionally omitted
        case DEADLINE:
            return logic.deadline(input, tasks.getList());
            // break intentionally omitted
        case EVENT:
            return logic.event(input, tasks.getList());
            // break intentionally omitted
        case DELETE:
            return logic.delete(input, tasks.getList());
            // break intentionally omitted
        case FIND:
            return logic.find(input, tasks.getList());
            // break intentionally omitted
        case ERROR:
            return ("Oops, that is not a command I support.");
            // break intentionally omitted
        case BYE:
            isExit = true;
            return ui.exitMessage();
            // break intentionally omitted
        default:
            return ("Internal error in code.");
        }
    }

    /**
     * Returns true if the command is BYE and false otherwise.
     *
     * @return True if command is BYE and false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
