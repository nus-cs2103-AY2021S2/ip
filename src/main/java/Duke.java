import main.java.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage dukeStorage;
    private TaskList dukeTaskList;
    private Ui dukeUi;

    final static String FILENAME = "dukedata.txt";
    final static String FOLDERNAME = "data";
    final static String PATH = FOLDERNAME + "/" + FILENAME;

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
