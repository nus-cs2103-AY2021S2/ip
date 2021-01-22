package commands;

import duke.Ui;
import duke.TaskManager;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String name;
    private LocalDate date;

    public EventCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public void execute(Ui ui, TaskManager tm) {
        tm.addEventTask(name, date);
    }
}
