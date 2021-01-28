package duke.customClass;

public class CommandRouter {
    private boolean isExit;

    public CommandRouter() {
        isExit = false;
    }

    public void route(Command c, TaskList tasks, String input) {
        LogicHandler logic = new LogicHandler();
        switch (c) {
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
            break;
        default:
            System.out.println("Internal error in code.");
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
