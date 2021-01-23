public class ExitCommand extends Command {

    @Override
    void executeAndPrint(TaskList list, int length) throws DukeException {
    }

    @Override
    boolean isExit() {
        return true;
    }
}
