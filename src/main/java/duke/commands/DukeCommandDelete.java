package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.TaskList;


/**
 * Delete command.
 *
 * Removes single or all tasks from task list, the latter executed using the 'delete all'
 * keyphrase.
 */
public class DukeCommandDelete extends DukeCommand {

    private final ArrayList<Integer> indices = new ArrayList<>();
    private boolean isDeleteAll = false;

    /**
     * Constructor to track task indices to delete.
     *
     * @param tokenSet user input tokens
     * @throws DukeExceptionIllegalArgument When indices cannot be parsed as integers
     */
    public DukeCommandDelete(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        if (tokenSet.contains("all")) {
            isDeleteAll = true;
            return;
        }

        /* Parse task numbers */
        try {
            String indices = tokenSet.get("/text");
            for (String s : indices.split("\\s+")) {
                this.indices.add(Integer.parseInt(s) - 1);
            }
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Need to specify task number to delete.");
        }
    }

    /**
     * Deletes tasks from tasklist, writes to file and displays success
     *
     * @param tasks tasklist
     * @param loader storage
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        if (isDeleteAll) {
            return executeDeleteAll(tasks, loader);
        } else {
            return executeDeleteSelected(tasks, loader);
        }
    }

    private Response executeDeleteSelected(TaskList tasks, FileLoader loader) {
        /* Attempt deletion */
        ArrayList<String> responseMessageArray = new ArrayList<>();
        responseMessageArray.add("Noted. I've removed these tasks:");
        try {
            for (int index : indices) {
                responseMessageArray.add("  " + tasks.getTask(index));
                tasks.deleteTask(index);
            }
            responseMessageArray.add("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeExceptionIllegalArgument e) {
            return Response.createResponseBad(e.getMessage());
        }

        /* Attempt to write to file */
        try {
            loader.write(tasks);
        } catch (DukeExceptionFileNotWritable e) {
            return Response.createResponseOk(e.getMessage());
        }
        return Response.createResponseOk(responseMessageArray.toArray(new String[0]));
    }

    private Response executeDeleteAll(TaskList tasks, FileLoader loader) {
        tasks.deleteAll();
        /* Attempt to write to file */
        try {
            loader.write(tasks);
        } catch (DukeExceptionFileNotWritable e) {
            return Response.createResponseOk(e.getMessage());
        }
        return Response.createResponseOk("All tasks successfully deleted.");
    }
}
