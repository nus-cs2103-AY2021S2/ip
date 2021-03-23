package seashell.command;

import seashell.TaskList;
import seashell.ui.Ui;

public class HelpCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return Ui.HELP_TEXT;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof HelpCommand;
    }
}
