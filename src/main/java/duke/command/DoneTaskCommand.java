package main.java.duke;

import main.java.duke.task.Task;

public class DoneTaskCommand extends Command {

    private int index;

    DoneTaskCommand(int index) {
        super();
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number does not work, try again?");
        }
        Task doneTask = tasks.find(index);
        doneTask.setDone();
        ui.speak("Swee! This task is done:");
        System.out.println(doneTask);
        storage.writeToFile(tasks);
    }
}
