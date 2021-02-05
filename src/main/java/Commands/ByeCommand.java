package Commands;

import Tasks.TaskList;

public class ByeCommand extends Command {

    public void execute(TaskList tasks) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
