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
            if (tm.indexWithinRange(doneIndex)) {
                Task task = tm.done(doneIndex);
                ui.displayAfterDone(task);
            } else {
                ui.displayOutOfRange(doneIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
