package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;

/**
 * Command to mark a task as done
 */
public class DoneCommand extends Command {
    private int doneIndex;

    /**
     * Creates a DoneCommand instance
     * @param doneIndex index of the task to be mark as done
     */
    public DoneCommand(int doneIndex) {
        super();
        this.doneIndex = doneIndex;
    }

    /**
     * execute done task command
     * call TaskManager to mark the particular task as done
     * and Ui to display done message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     */
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
