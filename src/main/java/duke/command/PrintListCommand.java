package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Command that prints the list existing in duke.TaskList.
 */
public class PrintListCommand implements ICommand {
    private TaskList tasks;

    public PrintListCommand(TaskList tasks){
        this.tasks = tasks;
    }

    @Override
    public void execute(String parameters) {
        System.out.println(listToString());
    }

    private String listToString() {
        String content="";
        Integer count = 1;
        for(Task t: tasks.getTasks()) {
            content += count.toString() + ".";
            content += t.toString();
            content += "\n";
            count++;
        }
        return content.trim();
    }
}
