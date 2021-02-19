package duke.commands;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Add command.
 *
 * Adds task to task list and writes changes to file.
 */
public class DukeCommandAdd extends DukeCommand {

    private final Task task;

    /** Constructor to track task */
    public DukeCommandAdd(Task task) {
        this.task = task;
    }

    /**
     * Returns Response after adding task to tasklist, writes to file and displays success
     *
     * @param tasks tasklist
     * @param loader storage
     * @return Response
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        tasks.addTask(task);

        /* Attempt to write to file */
        try {
            loader.write(tasks);
        } catch (DukeExceptionFileNotWritable e) {
            return Response.createResponseOk(e.toString());
        }

        /* Successful execution */
        String respondMessage = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.";
        return Response.createResponseOk(respondMessage);
    }
}
