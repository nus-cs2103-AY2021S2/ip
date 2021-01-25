public interface Command {
    void run(Storage storage, TaskList taskList) throws DukeException;
}
