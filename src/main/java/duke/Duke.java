package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class Duke {
    private Scanner keyboard;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath, String dirPath){
        keyboard = new Scanner(System.in);
        ui = new Ui();
        storage = new Storage(filePath,dirPath);

    }


    public void run(){
        TaskList taskList = new TaskList();
        // Read from storage
        try {
            taskList = storage.readTasks(taskList);
        } catch (Exception e) {
            ui.display("OOPS! There is something wrong: " + e.getMessage());
        }

        // In Execution
        ui.welcome();
        boolean isExit = false;
        while ( ! isExit) {
            try {
                String fullCommand = ui.readCommand(keyboard);
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.display(e.getMessage());
            }
        }

        // Save to files
        try {
            storage.saveTasks(taskList);
        } catch (Exception e) {
            ui.display("OOPS! There is something wrong: " + e.getMessage());
        }

    }




    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

          Duke duke = new Duke("./data/duke.txt","./data");
          duke.run();

    }
}
