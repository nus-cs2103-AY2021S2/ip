package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private String task;
    private LocalDateTime dateTime;

    public EventCommand(TaskList taskList, Ui ui, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, ui, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds an Event task with previously specified description and dateTime to taskList.
     */
    @Override
    public void execute() {
        Event e = new Event(this.task, this.dateTime);
        System.out.println("Got it. I've added this task:\n" + e);
        this.taskList.addTask(e);
    }
}
