package duke;

import duke.command.*;
import duke.task.Deadline;

import java.util.Scanner;

import static duke.Ui.displayAllTasks;
import static duke.Ui.displayError;
import static duke.task.TaskList.*;
import static duke.data.Data.updateDataFile;

public class Parser {

    private static CommandType getCommandType(String command) throws DukeException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (Exception e) {
            return CommandType.INVALID;
        }
    }

    public static void parseAndProcessInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                break;
            }
            String[] args = input.split(" ", 2);
            Command command;
            try {
                CommandType type = getCommandType(args[0]);
                switch (type) {
                case LIST:
                    command = new ListCommand();
                    break;
                case DONE:
                    command = new DoneCommand(args);
                    break;
                case TODO:
                    command = new TodoCommand(args);
                    break;
                case EVENT:
                    command = new EventCommand(args);
                    break;
                case DEADLINE:
                    command = new DeadlineCommand(args);
                    break;
                case DELETE:
                    command = new DeleteCommand(args);
                    break;
                case INVALID: default:
                    command = new InvalidCommand();
                }
                command.process();
                updateDataFile(Duke.tasks);
            } catch (DukeException e) {
                displayError(e.getMessage());
            }
        }
    }
}
