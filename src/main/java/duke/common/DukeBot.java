package duke.common;

import java.util.Scanner;

import duke.command.Command;
import duke.interaction.Parser;
import duke.interaction.Storage;
import duke.interaction.Ui;
import duke.task.TaskList;

/**
 * Instance of duke that keeps track of the current state.
 */
public class DukeBot {
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a new DukeBot with the associated input scanner, saving to the specified path.
     * @param path the path of the save file for persistent storage
     */
    public DukeBot(final String path) {
        this.parser = new Parser();
        this.storage = new Storage(path);
        this.taskList = this.storage.readTasks();
    }

    /**
     * Starts the input loop with the associated input stream, ending only on a bye command.
     *
     * @param scanner the scanner for the input stream.
     */
    public void run(Scanner scanner) {
        Command command;

        Ui.printOut(DukeString.MESSAGE_WELCOME);

        while (!parser.isBye()) {
            try {
                command = parser.parseInput(scanner.nextLine());
                Ui.printOut(command.execute(taskList));
                this.storage.writeTasks(taskList);
            } catch (DukeException.InvalidCommand
                    | DukeException.InvalidTask
                    | DukeException.InvalidDateFormat
                    | DukeException.EmptyDescription
                    | DukeException.EmptyDeadlineDate
                    | DukeException.EmptyEventDate
                    | DukeException.InvalidEventEnd e) {
                Ui.printErr(e.getMessage());
            }

        }
    }

    /**
     * Gets the appropriate response from Duke given an input.
     * @param input the input to Duke
     * @return the reply from Duke, if applicable. Otherwise, the error from Duke
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseInput(input);
            String out = command.execute(taskList);
            this.storage.writeTasks(taskList);
            return out;
        } catch (DukeException.InvalidCommand
                | DukeException.InvalidTask
                | DukeException.InvalidDateFormat
                | DukeException.EmptyDescription
                | DukeException.EmptyDeadlineDate
                | DukeException.EmptyEventDate
                | DukeException.InvalidEventEnd
                | DukeException.InvalidDateForTask e) {
            return e.getMessage();
        }
    }
}
