import java.time.LocalDate;

abstract class Command {
    protected String command;
    protected String description;
    protected String preposition;
    protected LocalDate date;
    protected boolean isExit;

    Command(String command, String description, String preposition, LocalDate date, boolean isExit) {
        this.command = command;
        this.description = description;
        this.preposition = preposition;
        this.date = date;
        this.isExit = isExit;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit() {
        return this.isExit;
    }
}
