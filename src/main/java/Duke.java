import Commands.Command;
import Exceptions.DukeException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import Parser.Parser;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            ui.preload();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            if (e.getMessage().equals("NoFile")) {
                ui.showNoLoadFileError();
            }
            if (e.getMessage().equals("MissingInfo")) {
                ui.showMissingInfoError(e.getInfo());
            }
        }
    }

    public void run() {
        ui.introduction();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                if (e.getMessage().equals("IndexOutOfBound")) {
                    ui.arrayIndexOutOfBoundsError();
                }
                if (e.getMessage().equals("EmptyIndex")) {
                    ui.emptyIndexError();
                }
                if (e.getMessage().equals("EmptyDescription")) {
                    ui.emptyDescriptionError(e.getInfo());
                }
                if (e.getMessage().equals("EmptyDetails")) {
                    ui.emptyDetailsError(e.getInfo());
                }
                if (e.getMessage().equals("UnknownCommand")){
                    ui.unknownCommandError();
                }
            }
        }
    }

//    public void run() {
//        try {
//            this.loadTasks();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.introduction();
//
//
//
//        boolean validState = true;
//
//        while (validState) {
//
//            } else {
//
//
//                try {
//                    this.saveTasks();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                line = sc.nextLine();
//            }
//        }
//
//        sc.close();
//    }


    public static void main(String[] args) throws DukeException, FileNotFoundException {
        Duke simulator = new Duke();
        simulator.run();
    }
}
