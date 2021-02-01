package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.dukeexceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke/tasks.txt";
    private static final Ui UI = new Ui();
    private static final Storage STORAGE = new Storage(FILE_PATH, UI);
    private static final TaskList TASKLIST = STORAGE.loadFromFile();

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Parser p = new Parser(TASKLIST, UI, STORAGE);
            Command c = p.parse(input);
            String output = c.execute();
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }

    }

//    private static void run() {
//        Ui ui = new Ui();
//        ui.introduction();
//
//        TaskList taskList = storage.loadFromFile();
//
//        Scanner scannerInput = new Scanner(System.in);
//        ui.showMsg("What can I do for you?");
//
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String input = scannerInput.nextLine();
//                Parser p = new Parser(taskList, ui, storage);
//                Command c = p.parse(input);
//                String output = c.execute();
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//        scannerInput.close();
//    }
//
//    public static void main(String[] args) {
//        run();
//    }
}
