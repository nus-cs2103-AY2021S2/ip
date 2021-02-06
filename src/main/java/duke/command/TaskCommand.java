package duke.command;


import duke.Storage;
import duke.TaskList;
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
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws IllegalArgumentException, IOException {
        Task newTask;
        switch(taskType) {
        case "todo":
            ToDo newToDo = new ToDo(this.description);
            taskList.addTask(newToDo);
            ui.printAddToDo(newToDo);
            storage.saveData(taskList);
            break;

        case "deadline":
            Deadline newDeadLine = new Deadline(this.description, this.time);
            taskList.addTask(newDeadLine);
            ui.printAddDeadLine(newDeadLine);
            storage.saveData(taskList);
            break;

        case "event":
            Event newEvent = new Event(this.description, this.time);
            taskList.addTask(newEvent);
            ui.printAddEvent(newEvent);
            storage.saveData(taskList);
            break;
        }


    }
}
