package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Duke program is a program that can help you
 * with the schedule management such as adding events, deadlines and todos
 * to it and it can also help you manage the tasks such as deleting tasks.
 *
 * Hope you like it!
 *
 * @author skinnychenpi
 * @since 2021-01-18
 */
public class Duke {
    private Scanner keyboard;
    private Storage storage;
    private Ui ui;

    /**
     * A constructor for Duke class
     *
     * @param filePath The file path that stores the task list.
     * @param dirPath The directory path that stores the directory of the task list file.
     */
    public Duke(String filePath, String dirPath){
        keyboard = new Scanner(System.in);
        ui = new Ui();
        storage = new Storage(filePath,dirPath);

    }

    /**
     * Start function to run the program.
     */
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



    /**
     * The main function of the program.
     */
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
