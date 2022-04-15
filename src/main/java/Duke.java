import duke.command.Command;
import duke.exceptions.DukeException;
import duke.maincomponents.Parser;
import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;

/**
 * Main class for Duke that handles the execution of its functions
 */
public class Duke {
    private static final String FILE_NAME = "dukedata.txt";
    private static final String FOLDER_NAME = "data";
    private static final String RELATIVE_PATH = FOLDER_NAME + "/" + FILE_NAME;
    private boolean isTerminated;
    private Storage dukeStorage;
    private TaskList dukeTaskList;
    private Ui dukeUi;
    private Parser stringParser;
    private boolean isCurrentError;

    /**
     * Default Constructor for Duke
     *
     * @param filePath path where the datafile storing the session is
     * @param folderName path of the folder storing the datafile
     */
    public Duke(String filePath, String folderName) {
        dukeUi = new Ui();
        stringParser = new Parser();
        isCurrentError = false;
        try {
            dukeStorage = new Storage(filePath, folderName);
            dukeTaskList = new TaskList(dukeStorage.load());
        } catch (DukeException e) {
            dukeUi.showErrorMsg(e.getMessage());
            dukeTaskList = new TaskList();
        }
        System.out.println();
    }

    /**
     * Runs the main loop of Duke program
     */
    public void run() {
        dukeUi.showWelcomeLine();

        String userInput = dukeUi.readCommand();
        boolean isExit = stringParser.checkIfExit(userInput);

        while (!isExit) {
            isCurrentError = false;
            try {
                Command c = stringParser.parse(userInput);
                c.execute(dukeTaskList, dukeUi, dukeStorage);
            } catch (DukeException e) {
                dukeUi.showErrorMsg(e.getMessage());
                isCurrentError = true;
            }
            userInput = dukeUi.readCommand();
            isExit = stringParser.checkIfExit(userInput);

            try {
                dukeStorage.saveToFile(dukeTaskList.getCurrentTaskList());
            } catch (DukeException e) {
                dukeUi.showErrorMsg(e.getMessage());
            }
        }
        dukeUi.showGoodbyeLine();
    }

    /**
     * Get Duke's Response for the GUI
     *
     * @param userInput String of the user's input
     * @return String of duke's output
     */
    public String getResponse(String userInput) {
        String toReturnString;
        boolean isExit = stringParser.checkIfExit(userInput);
        isCurrentError = false;
        if (!isExit) {
            try {
                Command c = stringParser.parse(userInput);
                toReturnString = c.executeGui(dukeTaskList, dukeUi, dukeStorage);
            } catch (DukeException e) {
                toReturnString = dukeUi.returnErrorMsg(e.getMessage());
                isCurrentError = true;
            }
            try {
                dukeStorage.saveToFile(dukeTaskList.getCurrentTaskList());
            } catch (DukeException e) {
                toReturnString = dukeUi.returnErrorMsg(e.getMessage());
                isCurrentError = true;
            }
            return toReturnString;
        } else {
            isTerminated = true;
            return getGoodbyeLine();
        }
    }

    public boolean getIsCurrentError() {
        return isCurrentError;
    }

    public String getGoodbyeLine() {
        return dukeUi.returnGoodbyeLine();
    }

    public boolean getIsTerminated() {
        return isTerminated;
    }

    public static void main(String[] args) {
        new Duke(RELATIVE_PATH, FOLDER_NAME).run();
    }

}
