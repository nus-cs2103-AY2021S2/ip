public interface Command {
    void run(Storage storage) throws DukeException;
}
