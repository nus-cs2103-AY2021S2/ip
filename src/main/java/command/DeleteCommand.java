package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

public class DeleteCommand extends Command {
    int deleteIndex;
    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskManager tm, Ui ui) {
        try {
            if (tm.indexWithinRange(deleteIndex)) {
                Task task = tm.deleteTask(deleteIndex);
                ui.displayAfterDelete(deleteIndex, task);
            } else {
                ui.displayOutOfRange(deleteIndex);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
