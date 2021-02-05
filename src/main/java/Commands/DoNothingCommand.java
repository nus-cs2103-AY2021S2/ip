package Commands;

import Tasks.TaskList;

public class DoNothingCommand extends Command {

    public void execute(TaskList tasks) {
    }

    public boolean isExit() {
        return false;
    }
}
