package jeff;

import java.util.ArrayList;

public class CommandFind extends Command {

    CommandFind(String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        ArrayList<Task> foundTasks = tasks.findTask(mainInfo);
        String s = "Here are the tasks I found matching \"" + mainInfo + "\":\n";
        for (Task t : foundTasks) {
            s += t.toString() + "\n";
        }
        return s;
    }
}
