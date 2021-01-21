import java.util.Locale;
import java.util.Scanner;

public class Parser {
    private boolean bye;

    public Parser() {
        this.bye = false;
    }

    public boolean isBye() {
        return this.bye;
    }

    public Command parseInput(final String input) throws InvalidCommandException {
        Scanner scanner = new Scanner(input);

        if (!scanner.hasNext()) {
            throw new InvalidCommandException();
        }
        switch (scanner.next().toLowerCase(Locale.ROOT)) {
            case DukeStrings.COMMAND_BYE:
                this.bye = true;
                return new ExitCommand();
            case DukeStrings.COMMAND_LIST:
                return new ListCommand();
            case DukeStrings.COMMAND_DONE:
                return new DoneCommand(scanner.nextInt());
            case DukeStrings.COMMAND_DEADLINE:
                return new DeadlineCommand(scanner.nextLine());
            case DukeStrings.COMMAND_EVENT:
                return new EventCommand(scanner.nextLine());
            case DukeStrings.COMMAND_TODO:
                return new TodoCommand(scanner.nextLine());
            default:
                throw new InvalidCommandException();

        }
    }
}
