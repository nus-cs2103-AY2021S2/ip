package commands;

import format.Ui;
import tasklist.TaskList;

public class ByeCommand extends CommandWithNoParameters {

    public ByeCommand(String commandBody) {
        super("bye", commandBody);
    }

    @Override
    public void run(TaskList taskList) {
        assert commandBody.trim().equals(commandBody) :
                "parser is not sending trimmed strings to commands. some commands operate on this assumption";

        if (!commandBody.isEmpty()) {
            handleTooManyArgs();
            return;
        }

        this.hasSentExitDukeSignal = true;
        this.hasRunSuccessfully = true;
        this.commandOutputMsg = Ui.getExitMessage();

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
