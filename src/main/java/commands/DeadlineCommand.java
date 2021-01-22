package commands;

import duke.Ui;
import duke.TaskManager;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String name;
    private LocalDate date;

    public DeadlineCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public void execute(Ui ui, TaskManager tm) {
        tm.addDeadlineTask(name, date);
    }
}
