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

    public Command parseInput(final String input) {
        Scanner scanner = new Scanner(input);

        switch (scanner.next().toLowerCase(Locale.ROOT)) {
            case DukeStrings.COMMAND_BYE:
                this.bye = true;
                return new ExitCommand();
            case DukeStrings.COMMAND_LIST:
                return new ListCommand();
            case DukeStrings.COMMAND_DONE:
                return new DoneCommand(scanner.nextInt());
            default:
                return new AddCommand(input);
        }
    }
}
