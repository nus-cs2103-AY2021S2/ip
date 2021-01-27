package commands;

import java.io.IOException;

import data.TaskList;
import ui.TextUi;

public abstract class Command {
    protected Command() {}

    public abstract void execute(TaskList tasks, TextUi ui) throws IOException;
}
