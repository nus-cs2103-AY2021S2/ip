import java.util.Locale;

public class Parser {
    private boolean bye;

    public Parser() {
        this.bye = false;
    }

    public boolean isBye() {
        return this.bye;
    }

    public Command parseInput(final String input) {
        switch (input.toLowerCase(Locale.ROOT)) {
            case DukeStrings.COMMAND_BYE:
                this.bye = true;
                return new ExitCommand();
            default:
                return new AddCommand(input);
        }
    }
}
