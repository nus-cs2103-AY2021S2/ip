package main.java.command;

import main.java.classes.DuckieException;
import main.java.classes.Storage;
import main.java.classes.TaskList;
import main.java.classes.Ui;

public class ByeCmd extends Command {

    private String cmd;

    public ByeCmd (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException {
        return;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
