public abstract class Command {

    protected Ui userInterface;
    protected Storage storage;
    protected TaskList taskList;
    protected Parser parser;

    public Command(Parser parser, Ui ui, Storage storage) {

        userInterface = ui;
        this.parser = parser;
        this.storage = storage;

        try {
            taskList = new TaskList(storage.readFile());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    abstract String execute() throws DukeException;

}
