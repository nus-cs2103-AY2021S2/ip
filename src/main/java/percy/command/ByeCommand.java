package percy.command;

import percy.ui.UserInterface;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    public void execute() {
    }

    public boolean isExit() {
        return true;
    }
}