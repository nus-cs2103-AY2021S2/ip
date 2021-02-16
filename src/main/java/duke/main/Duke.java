package duke.main;

import java.util.ArrayList;

import duke.commands.DukeCommand;
import duke.commands.factory.DukeCommandFactory;
import duke.exceptions.DukeException;
import duke.exceptions.DukeExceptionFileNotAccessible;
import duke.exceptions.DukeExceptionFileNotWritable;
import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.parser.UserInputTokenizer;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.PreformattedMessages;


/**
 * Main logic class for tasklist management.
 */
public class Duke {
    private static final String DATAFILE_PATH = "./dukeData/tasks.txt";

    private FileLoader loader;
    private TaskList tasks;

    /** Empty constructor used for initialization */
    public Duke() {}

    /**
     * Initializes the main program given directory used to save user tasks.
     *
     * Attempts to load the task list from the file, displaying the status
     * of the task list depending on load success.
     */
    public String getStartupResponse() {
        String response = PreformattedMessages.WELCOME_SCREEN;
        try {
            loader = new FileLoader(DATAFILE_PATH);
            tasks = loader.read();
            loader.throwIfNotWritable(); // can read but cannot write
            response += PreformattedMessages.getLoadingSuccessMessage(tasks.size());

        } catch (DukeExceptionFileNotWritable e) {
            response += PreformattedMessages.getFileWriteErrorMessage(tasks.size());

        } catch (DukeExceptionFileNotAccessible e) {
            tasks = new TaskList();
            response += PreformattedMessages.getFileReadErrorMessage();

        } catch (DukeExceptionIllegalArgument e) {
            tasks = new TaskList();
            response += PreformattedMessages.getFileLoadErrorMessage();
        }
        return response;
    }

    /**
     * Retrieves tasklist as a string for immediate printing in GUI.
     *
     * @return String
     */
    public String getTasklistString() {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTaskUnsafe(i);
            lines.add(tasks.getLeftPadding(i + 1) + (i + 1) + ". " + task.toString());
        }
        return String.join("\n", lines.toArray(new String[0]));
    }

    /**
     * Retrieves result of parsing and processing of user input.
     *
     * @param input User input text
     * @return Response from Duke
     */
    public Response getResponse(String input) {
        try {
            UserInputTokenSet tokenSet = UserInputTokenizer.parse(input);
            DukeCommand cmd = DukeCommandFactory.getDukeCommand(tokenSet);
            return cmd.execute(tasks, loader);
        } catch (DukeException e) {
            return Response.createResponseBad(e.toString());
        }
    }
}
