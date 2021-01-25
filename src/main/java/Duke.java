import duke.commands.DukeCommand;
import duke.exceptions.*;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private FileLoader loader;
    private TaskList tasks;
    private Ui ui;
    private boolean isLocalTaskList; // in event file cannot be written to

    /**
     * Initializes the main program given directory used to save user tasks.
     *
     * Attempts to load the task list from the file, displaying the status
     * of the task list depending on load success.
     *
     * @param filePath UNIX-like directory path to file.
     *                 Does not need to be initialized beforehand.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcomeScreen();
        isLocalTaskList = false;
        try {
            loader = new FileLoader(filePath);
            tasks = loader.read();
            loader.throwIfNotWritable(); // can read but cannot write
            ui.showLoadingSuccess(tasks.size());

        } catch (DukeExceptionFileNotWritable e) {
            isLocalTaskList = true;
            ui.showFileWriteError(tasks.size());

        } catch (DukeExceptionFileNotAccessible e) {
            isLocalTaskList = true;
            tasks = new TaskList();
            ui.showFileLoadingError();

        } catch (DukeExceptionIllegalArgument e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Executes the main REPL for the program.
     */
    public void run() {
        boolean isProgramTerminating = false;
        while (!isProgramTerminating) {
            try {
                String input = ui.getUserInput(">>> ");
                DukeCommand cmd = DukeCommand.parse(input);
                cmd.execute(tasks, ui, loader);
                isProgramTerminating = cmd.isExit();
            } catch (DukeExceptionFileNotWritable e) {
                if (isLocalTaskList) {
                    continue;
                }
                ui.showError(e);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./dukeData/tasks.txt").run();
    }
}