package commands;

import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import exceptions.DukeException;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String name;
    private LocalDate date;

    public EventCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public void execute(Ui ui, TaskManager tm, Storage st) throws DukeException {
        tm.addEventTask(name, flag);
        st.save(tm);
    }
}
