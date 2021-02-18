package jeff;

import java.util.ArrayList;

public class CommandFind extends Command {

    CommandFind(String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        ArrayList<Task> foundTasks = tasks.findTask(getMainInfo());
        String s = "Here are the tasks I found matching \"" + getMainInfo() + "\":\n";
        for (Task t : foundTasks) {
            s += t.toString() + "\n";
        }
        return s;
    }
}
