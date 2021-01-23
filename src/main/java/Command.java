public abstract class Command {
    abstract void executeAndPrint(TaskList list, int length) throws DukeException;
    abstract boolean isExit();
}
