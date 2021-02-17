package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


public class TaskCommand extends Command {
    private LocalDateTime time = null;
    private final String description;
    private final String taskType;

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
            return ui.showAdd(newToDo, taskList);

        case "deadline":
            Deadline newDeadLine = new Deadline(this.description, this.time);
            taskList.addTask(newDeadLine);
            storage.saveData(taskList);
            return ui.showAdd(newDeadLine, taskList);

        case "event":
            Event newEvent = new Event(this.description, this.time);
            taskList.addTask(newEvent);
            storage.saveData(taskList);
            return ui.showAdd(newEvent, taskList);

        default:
            throw new DukeCommandException("Invalid Command.");
        }


    }
}
