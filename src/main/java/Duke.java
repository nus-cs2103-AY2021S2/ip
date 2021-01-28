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
    private Storage dukeStorage;
    private TaskList dukeTaskList;
    private Ui dukeUi;

    final static String FILENAME = "dukedata.txt";
    final static String FOLDERNAME = "data";
    final static String PATH = FOLDERNAME + "/" + FILENAME;

    /**
     * Default Constructor for Duke
     * @param filePath path where the datafile storing the session is
     * @param folderName path of the folder storing the datafile
     */
    public Duke(String filePath,String folderName) {
        dukeUi = new Ui();
        try {
            dukeStorage = new Storage(filePath, folderName);
            dukeTaskList = new TaskList(dukeStorage.load());
            dukeUi.showLoadingSucess();
        } catch (DukeException e) {
            dukeUi.showLoadingError(e.getMessage());
            dukeTaskList = new TaskList();
        }
    }

    /**
     * Runs the main loop of Duke program
     */
    public void run() {
        Parser stringParser = new Parser();
        dukeUi.showWelcomeLine();

        boolean isExit = false;
        String userInput = dukeUi.readCommand();

        while ( !isExit){
            try {
                Command c = stringParser.parse(userInput);
                c.execute(dukeTaskList, dukeUi, dukeStorage);
            } catch (DukeException e) {
                dukeUi.showErrorMsg(e.getMessage());
            }
            userInput = dukeUi.readCommand();
            isExit = stringParser.checkIfExit(userInput);

            try {
                dukeStorage.saveToFile(dukeTaskList.getCurrentTaskList());
            }catch(DukeException e){
                dukeUi.showErrorMsg(e.getMessage());
            }
        }
        dukeUi.showGoodbyeLine();
    }

    public static void main(String[] args) {
        new Duke(PATH, FOLDERNAME).run();
    }
}
