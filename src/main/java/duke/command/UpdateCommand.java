package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateCommand extends Command {
    private int index;
    private String taskName;
    private LocalDate taskDate;

    /**
     * Creates a update command object for updating task name
     * @param index
     * @param taskName
     */
    public UpdateCommand(int index, String taskName) {
        super();
        this.index = index - 1;
        this.taskName = taskName;
    }

    /**
     * Creates a update command object for updating task date
     * @param index Index for task needed to be updated
     * @param taskDate New task date to be updated
     */
    public UpdateCommand(int index, LocalDate taskDate) {
        super();
        this.index = index - 1;
        this.taskDate = taskDate;
    }

    /**
     * Creates a update command object for updating both task name and task date
     * @param index Index for task needed to be updated
     * @param taskName New task name to be updated
     * @param taskDate New task date to be updated
     */
    public UpdateCommand(int index, String taskName, LocalDate taskDate) {
        super();
        this.index = index - 1;
        this.taskName = taskName;
        this.taskDate = taskDate;
    }

    /**
     * Execute action to update a task based on index and condition (name or date)
     * @param tasks list of tasks
     * @param ui UI required for conversation
     * @param storage Storage required for .txt file
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> returnMsg = new ArrayList<>();
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("The task number does not exist, try again?");
        }
        Task updateTask = tasks.find(index);
        if (this.taskName != null) {
            updateTask.updateName(this.taskName);
            returnMsg.add(ui.speak("I've updated your task name to: " + this.taskName + "."));
        }
        if (this.taskDate != null) {
            if (updateTask.getDate() == null) throw new DukeException("Your task does not support dates!");
            updateTask.updateDate(this.taskDate);
            returnMsg.add(ui.speak("I've updated your task date to: " + this.taskDate + "."));
        }

        storage.writeToFile(tasks);
        return returnMsg;
    }
}
