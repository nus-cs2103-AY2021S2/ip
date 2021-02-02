package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.utils.Storage;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String task;
    private LocalDateTime dateTime;

    /**
     * Creates a DeadlineCommand object to store the deadline command input from the user.
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param task the task input by the user.
     * @param dateTime the date and time associated with the task.
     */
    public DeadlineCommand(TaskList taskList, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds a Deadline task with previously specified description and dateTime to taskList.
     * @return confirmation message for added Deadline Task.
     */
    @Override
    public String execute() {
        Deadline d = new Deadline(this.task, this.dateTime);
        String msg = "Got it. I've added this task:\n" + d;
        this.taskList.addTask(d);
        return msg;
    }
}
