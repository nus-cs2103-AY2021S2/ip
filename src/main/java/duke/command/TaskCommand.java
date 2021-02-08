package duke.command;


import duke.Storage;
import duke.TaskList;
import duke.exception.DukeCommandException;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class TaskCommand extends Command {
    private LocalDateTime time = null;
    private String description;
    private String taskType;

    public TaskCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public TaskCommand(String taskType, String description, LocalDateTime time) {
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws IOException, DukeCommandException {

        switch(taskType) {
        case "todo":
            ToDo newToDo = new ToDo(this.description);
            taskList.addTask(newToDo);
            storage.saveData(taskList);
            return ui.showAddToDo(newToDo);

        case "deadline":
            Deadline newDeadLine = new Deadline(this.description, this.time);
            taskList.addTask(newDeadLine);
            storage.saveData(taskList);
            return ui.showAddDeadLine(newDeadLine);

        case "event":
            Event newEvent = new Event(this.description, this.time);
            taskList.addTask(newEvent);
            storage.saveData(taskList);
            return ui.showAddEvent(newEvent);

        default:
            throw new DukeCommandException("Invalid Command.");
        }


    }
}
