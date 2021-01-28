package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.time.LocalDate;

public abstract class Command {
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
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }
}
