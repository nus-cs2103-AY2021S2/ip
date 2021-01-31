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

    /**
     * Creates a EventCommand object to store the event command input from the user.
     * @param taskList the current list of Tasks.
     * @param ui the object in charge of printing user-friendly outputs.
     * @param storage the object in charge of writing to the local storage file.
     * @param task the task input by the user.
     * @param dateTime the date and time associated with the task.
     */
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
