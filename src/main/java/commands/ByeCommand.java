package commands;

public class ByeCommand extends CommandWithNoParameters {

    public ByeCommand(String commandBody) {
        super("bye", commandBody);
    }

    @Override
    public void run() {
        // make sure that parser always sends trimmed strings?
        if (commandBody == null) {
            this.hasSentExitDukeSignal = true;
        }
    }
}
