package duke.commands;

import duke.tasks.TaskList;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(CommandType.HELP);
    }

    @Override
    public String execute(TaskList list) {
        return ui.printHelpMessage();
    }
}
