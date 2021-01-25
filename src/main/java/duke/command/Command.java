package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class Command {

    public Command() { }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}
    public boolean isExit() {
        return false;
    }
}
