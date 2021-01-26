package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class Command {

    protected String reply;
    protected boolean isBye = false;

    public Command(String reply) {
        this.reply = reply;
    }

    public boolean getIsBye() {
        return this.isBye;
    }

    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
    }
}
