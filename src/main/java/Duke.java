import java.io.IOException;
import java.util.*;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.exception.DukeToDoException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePathStr) {
        ui = new Ui();
        storage = new Storage(filePathStr);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try{
                String fullCommand = ui.getUserCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | DukeCommandException e) {
                ui.showError(e.getMessage());
            } catch (DukeToDoException e) {
                 ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    // public static void main(String[] args) throws IOException {
    //     System.out.println("Hello, I'm Bob");
    //     Scanner scanner = new Scanner(System.in);
    //     Storage storage = new Storage();
    //     List<Task> savedTaskList = storage.loadData();
    //
    //     InputHandler io = new InputHandler(savedTaskList);
    //
    //     while (true) {
    //         String input = scanner.nextLine();
    //         if (input.equals("bye")) {
    //             System.out.println(">>> Bye. Hope to see you again soon!");
    //             break;
    //         } else {
    //             io = io.processInput(input);
    //             storage.writeData(io.getTaskList());
    //         }
    //     }
    // }
}
