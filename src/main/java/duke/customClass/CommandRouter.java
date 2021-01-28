package duke.customClass;

/**
 * Matches the command called by a user to the command logic to be called in LogicHandler.
 */
public class CommandRouter {
    private boolean isExit;

    public CommandRouter() {
        isExit = false;
    }

    /**
     * Maps the string of the command name given in the user's input to the enumerated command handled by LogicHandler.
     * If no such command exists or there is an error, it will be mapped to the error command.
     * @param command Command specified.
     * @param tasks TaskList object.
     * @param input input of the user as a String.
     */
    public void route(Command command, TaskList tasks, String input) {
        LogicHandler logic = new LogicHandler();
        Ui ui = new Ui();

        switch (command) {
        case LIST:
            logic.list(tasks.getList());
            break;
        case DONE:
            logic.done(input, tasks.getList());
            break;
        case TODO:
            logic.todo(input, tasks.getList());
            break;
        case DEADLINE:
            logic.deadline(input, tasks.getList());
            break;
        case EVENT:
            logic.event(input, tasks.getList());
            break;
        case DELETE:
            logic.delete(input, tasks.getList());
            break;
        case FIND:
            logic.find(input, tasks.getList());
            break;
        case ERROR:
            System.out.println("Oops, that is not a command I support.");
            break;
        case BYE:
            isExit = true;
            ui.exitMessage();
            break;
        default:
            System.out.println("Internal error in code.");
        }
    }

    /**
     * returns true if the command is BYE and false otherwise.
     * @return true if command is BYE and false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
