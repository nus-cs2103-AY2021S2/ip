import java.io.*;
import java.util.Scanner;

public class Duke {
    private Storage store;
    private Ui ui;
    private TaskList taskList;
    private int count;

    private static final String LOG_PATH = "./logs";

    public Duke(String path) {
        this.store = new Storage(path);
        this.taskList = new TaskList(this.store);
        this.count = 0;
        this.ui = new Ui();
    }

    public int dukeRunner() {
        Ui.printHello();

        while (true) {
            String userInput = ui.getInputFromUser();

            ParserOutput parserOutput = null;
            try {
                parserOutput = Parser.parse(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue;
            }

            ui.printHRule();

            if (parserOutput.isBye()) {
                break;
            }

            this.readParse(parserOutput);

            ui.printHRule();
        }

        Ui.printGoodbye();

        return 0;
    }

    private void readParse(ParserOutput parserOutput) {
        switch (parserOutput.getAction()) {
            case 1: //remove
                this.taskList.remove(parserOutput.getIndex());
                ui.printRemoved(parserOutput.getIndex());
                break;
            case 2: //done
                Task doneTask = this.taskList.get(parserOutput.getIndex());
                doneTask.markDone();
                this.taskList.set(parserOutput.getIndex(), doneTask);
                ui.printDone(doneTask);
                break;
            case 3: //add
                this.taskList.add(parserOutput.getTask());
                ui.printAdded(parserOutput.getTask(), this.taskList.getSize());
                break;
            case 4: //set
                this.taskList.set(parserOutput.getIndex(), parserOutput.getTask());
                break;
            case 5: //list
                ui.printList(this.taskList);
                break;
            default:
                //TODO: add exception for this
        }
    }

    public static void main(String[] args) {
        File logFile = new File(LOG_PATH);

        try {
            if (logFile.isFile()) {
                Scanner logs = new Scanner(logFile);
                logs.close();
            } else {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("no file created " + e);
        }

        new Duke(LOG_PATH).dukeRunner();
    }
}
