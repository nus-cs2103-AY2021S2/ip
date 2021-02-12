package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidCommandException;


public class Duke {

    /**
     * Main application code
     *
     * @param args Unused arguments
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printStartUp();
        TaskList taskList;
        try {
            ui.printLoadStart();
            taskList = Storage.loadTaskList();
            ui.printLoadSuccess();
        } catch (IOException e) {
            ui.printLoadFail();
            return;
        }
        Scanner in = new Scanner(System.in);
        String line;
        do {
            ui.printPrompt();
            line = in.nextLine();
            try {
                Command c = Parser.parse(line);
                if (c == null) { //Bye command
                    break;
                }
                String data = taskList.run(c);
                ui.printCommandMessage(c, data);
            } catch (ParseException e) {
                ui.handleException(e);
            } catch (InvalidCommandException e) {
                ui.handleException(e);
            } catch (EmptyArgumentException e) {
                ui.handleException(e);
            } catch (BadDateArgumentException e) {
                ui.handleException(e);
            } finally {
                if (taskList.isEdited()) {
                    try {
                        Storage.saveTaskList(taskList);
                        taskList.markSaved();
                    } catch (IOException e) {
                        ui.dumpState(taskList);
                    }
                }
            }
        } while (true);
        ui.printShutDown();
        in.close();
    }
}
