package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Ui ui;
    private final TaskList taskLst;
    private Storage storage;

    /**
     * The constructor of Duke. Components needed to run Duke should be initialized here.
     */
    public Duke() {
        ui = new Ui();
        taskLst = new TaskList();

        // Terminate if unable to initialise storage
        try {
            storage = new Storage();
            storage.fillTaskLst(taskLst);
        } catch (IOException e) {
            ui.print(String.format("Unable to initialise duke.storage.Storage: %s", e));
            System.exit(1);
        }
    }

    /**
     * The run method of Duke CLI. Execute `new Duke().run()` to start Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        ui.printWelcomeMsg();

        while (!isExit) {
            try {
                String input = sc.nextLine();
                Command cmd = Parser.parse(input);

                String resp = cmd.execute(taskLst);
                isExit = cmd.isExit();
                storage.saveTaskLst(taskLst);

                ui.print(resp);

            } catch (DukeException e) {
                ui.print(e.getMessage());

            } catch (Exception e) {
                // For some reason, stackTrace can only be printed at the front or back of a single response.
                e.printStackTrace();

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("[ERROR] %s: %s\n", e.getClass().getCanonicalName(), e.getMessage()));
                sb.append(ui.getDefaultErrorMessage());
                ui.print(sb.toString());
            }
        }
    }

    public String getResponse(String input) {
        Command cmd = Parser.parse(input);
        String resp = cmd.execute(taskLst);
        storage.saveTaskLst(taskLst);
        return resp;
    }

    public boolean isExitInput(String input) {
        Command cmd = Parser.parse(input);
        return cmd.isExit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
