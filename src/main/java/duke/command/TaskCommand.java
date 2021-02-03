package duke.command;

import duke.*;

import java.io.IOException;
import java.time.LocalDate;

public class TaskCommand extends Command {
    private LocalDate time = null;
    private String description;
    private String taskType;

    public TaskCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    public TaskCommand(String taskType, String description, LocalDate time) {
        this.taskType = taskType;
        this.description = description;
        this.time = time;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IllegalArgumentException {
        Task newTask;
        switch(taskType) {
        case "todo":
            ToDo newToDo = new ToDo(this.description);
            taskList.addTask(newToDo);
            ui.printAddToDo(newToDo);
            break;

        case "deadline":
            Deadline newDeadLine = new Deadline(this.description, this.time);
            taskList.addTask(newDeadLine);
            ui.printAddDeadLine(newDeadLine);
            break;

        case "event":
            Event newEvent = new Event(this.description, this.time);
            taskList.addTask(newEvent);
            ui.printAddEvent(newEvent);
            break;
        }


    }
}
