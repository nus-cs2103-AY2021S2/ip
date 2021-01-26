package duke.commands;

import duke.tasks.TaskList;

public class ByeCommand extends Command{
    public ByeCommand(){
        super(CommandType.EXIT);
    }

    @Override
    public void excecute(TaskList list) {
        ui.printGoodbye();
    }
}
