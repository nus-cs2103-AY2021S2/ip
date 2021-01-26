package duke.command;

import duke.Com;

public class Command {
    Com command;
    String arguments;

    public Command(Com command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Com getCommand() {
        return this.command;
    }
    public String getArguments() {
        return this.arguments;
    }
}
