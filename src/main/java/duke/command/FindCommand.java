package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {

    public FindCommand(String task, String date) {
        super("find", task, date, command -> {
            System.out.println(TaskList.find(task));
            return false;
        });
    }


}
