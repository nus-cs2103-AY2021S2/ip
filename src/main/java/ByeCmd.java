package main.java;

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
