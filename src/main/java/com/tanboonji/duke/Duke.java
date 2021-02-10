package com.tanboonji.duke;

import java.util.Scanner;

import com.tanboonji.duke.command.Command;
import com.tanboonji.duke.command.HelpCommand;
import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.TaskList;
import com.tanboonji.duke.parser.CommandParser;
import com.tanboonji.duke.storage.Storage;
import com.tanboonji.duke.ui.Ui;

/**
 * The Duke class manages the task list and data storage, executes commands and return command responses.
 */
public class Duke {

    private static final String FILE_DIR = "duke.txt";

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    private void run() {
        storage = new Storage(FILE_DIR);
        ui = new Ui();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        ui.greet();

        Scanner scanner = new Scanner(System.in);
        boolean continueDuke = true;

        while (continueDuke) {
            String input = scanner.nextLine().trim();
            continueDuke = processInput(input);
        }
    }

    /**
     * Initialises Duke by loading task list from storage.
     */
    public void initialise() {
        storage = new Storage(FILE_DIR);
        ui = new Ui();

        try {
            taskList = storage.load();
            ui.print("load successful");
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        ui.greet();
    }

    private boolean processInput(String input) {
        Command command;
        try {
            command = CommandParser.parse(input);
            command.addTaskList(taskList);
            String result = command.execute();
            ui.print(result);

            if (command.shouldSave()) {
                storage.save(taskList);
            }

            if (command.shouldExit()) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            ui.print(HelpCommand.ERROR_MESSAGE + HelpCommand.COMMAND_LIST);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        return true;
    }

    /**
     * Executes user command and returns response from command.
     *
     * @param input user command.
     * @return String response from executing user command.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = CommandParser.parse(input);
            command.addTaskList(taskList);
            String result = command.execute();

            if (command.shouldSave()) {
                storage.save(taskList);
            }

            return result;
        } catch (IllegalArgumentException e) {
            return HelpCommand.ERROR_MESSAGE + HelpCommand.COMMAND_LIST;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
