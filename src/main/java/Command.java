import java.util.Locale;

public class Command {
    private CommandType type;
    private String description;
    public Command(CommandType type) {
        this.type = type;
    }
    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    public CommandType getCommand() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getCommandName() {
        return type.toString().toLowerCase(Locale.ROOT);
    }
}
