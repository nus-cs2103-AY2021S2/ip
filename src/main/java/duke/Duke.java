package duke;
import duke.system.Parser;
import duke.system.Storage;
import duke.system.Ui;
import duke.system.exception.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        Parser in = new Parser();
        Scanner sc= new Scanner(System.in);
        ui.showLine();
        ui.showText("Hello! I'm Duke \nWhat can I do for you? \n");
        ui.showLine();
        while(!in.getCommand().equals("bye")){
            in = new Parser(sc.nextLine());
            ui.showText(in.print(tasks));
            try {
                storage.writeData(tasks.toString());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
