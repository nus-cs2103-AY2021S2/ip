package duke.interaction;

import java.util.Scanner;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.common.DukeException;
import duke.common.DukeString;


public class Parser {
    private boolean bye;

    public Parser() {
        this.bye = false;
    }

    public boolean isBye() {
        return this.bye;
    }

    public Command parseInput(final String input) throws DukeException.InvalidCommand, DukeException.EmptyDescription {
        Scanner scanner = new Scanner(input);

        if (!scanner.hasNext()) {
            throw new DukeException.InvalidCommand();
        }
        switch (scanner.next().toLowerCase()) {
        case DukeString.COMMAND_BYE:
            this.bye = true;
            return new ExitCommand();
        case DukeString.COMMAND_LIST:
            return new ListCommand();
        case DukeString.COMMAND_DONE:
            if (!scanner.hasNextInt()) {
                throw new DukeException.InvalidTask();
            }
            return new DoneCommand(scanner.nextInt());
        case DukeString.COMMAND_DELETE:
            if (!scanner.hasNextInt()) {
                throw new DukeException.InvalidTask();
            }
            return new DeleteCommand(scanner.nextInt());
        case DukeString.COMMAND_DEADLINE:
            if (!scanner.hasNext()) {
                throw new DukeException.EmptyDescription(DukeString.COMMAND_DEADLINE);
            }
            return new DeadlineCommand(scanner.nextLine());
        case DukeString.COMMAND_EVENT:
            if (!scanner.hasNext()) {
                throw new DukeException.EmptyDescription(DukeString.COMMAND_EVENT);
            }
            return new EventCommand(scanner.nextLine());
        case DukeString.COMMAND_TODO:
            if (!scanner.hasNext()) {
                throw new DukeException.EmptyDescription(DukeString.COMMAND_TODO);
            }
            return new TodoCommand(scanner.nextLine().trim());
        default:
            throw new DukeException.InvalidCommand();

        }
    }
}
