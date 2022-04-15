package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents a deadline command which adds a task with a deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String task;
    private LocalDateTime dateTime;

    /**
     * Creates a DeadlineCommand object to store the deadline command input from the user.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param task the String task input by the user.
     * @param dateTime the date and time associated with the task.
     */
    public DeadlineCommand(TaskList taskList, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds a Deadline task with previously specified description and dateTime to taskList.
     *
     * @return confirmation message for added Deadline Task.
     */
    @Override
    public String execute() {
        Deadline deadline = new Deadline(this.task, this.dateTime);
        String successAddMsg = "Got it. I've added this task:\n" + deadline;
        this.taskList.addTask(deadline);
        return successAddMsg;
    }
}
