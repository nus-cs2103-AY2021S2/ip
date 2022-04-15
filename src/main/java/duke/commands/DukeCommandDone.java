package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.TaskList;

/**
 * Done command.
 *
 * Marks task as done and saves task list to file.
 */
public class DukeCommandDone extends DukeCommand {

    private final ArrayList<Integer> indices = new ArrayList<>();

    /**
     * Constructor to track task indices to mark as done.
     *
     * @param tokenSet user input tokens
     * @throws DukeExceptionIllegalArgument When indices cannot be parsed as integers
     */
    public DukeCommandDone(UserInputTokenSet tokenSet) throws DukeExceptionIllegalArgument {
        try {
            String indices = tokenSet.get("/text");
            for (String s : indices.split("\\s+")) {
                this.indices.add(Integer.parseInt(s) - 1);
            }
        } catch (Exception e) {
            throw new DukeExceptionIllegalArgument("Need to specify task number to mark as done.");
        }
    }

    /**
     * Returns Response indicating tasks marked as done in tasklist, writes to file and displays success
     *
     * @param tasks tasklist
     * @param loader storage
     * @return Response
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        ArrayList<String> responseMessageArray = new ArrayList<>();
        responseMessageArray.add("Nice! I've marked these tasks as done:");
        try {
            for (int index : indices) {
                tasks.setDone(index);
                responseMessageArray.add("  " + tasks.getTask(index));
            }
        } catch (DukeExceptionIllegalArgument e) {
            return Response.createResponseBad(e.toString());
        }

        /* Attempt to write to file */
        try {
            loader.write(tasks);
        } catch (DukeExceptionFileNotWritable e) {
            return Response.createResponseOk(e.toString());
        }
        return Response.createResponseOk(responseMessageArray.toArray(new String[0]));
    }
}
