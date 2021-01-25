package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

public class DoneCommand extends Command {
    int doneIndex;
    public DoneCommand(int doneIndex) {
        super();
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskManager tm, Ui ui) {
        try {
            Task task = tm.done(doneIndex);
            ui.displayAfterDone(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
