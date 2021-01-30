package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Parses and processes user input.
 */
public class Parser {

    /**
     * Returns a CommandType object which identifies the type of command was passed by the user.
     *
     * @param command a string which when valid, should correspond to one of the CommandType objects
     * @return CommandType object
     * @throws DukeException if the command string does not correspond to any of the valid CommandType objects
     */
    private static CommandType getCommandType(String command) throws DukeException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (Exception e) {
            return CommandType.INVALID;
        }
    }

    /**
     * Parses user input line-by-line and processes user input accordingly.
     */
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
                case FIND:
                    command = new FindCommand(args);
                    break;
                case INVALID:
                default:
                    command = new InvalidCommand();
                }
                command.process();
            } catch (DukeException e) {
                Ui.displayError(e.getMessage());
            }
        }
    }
}
