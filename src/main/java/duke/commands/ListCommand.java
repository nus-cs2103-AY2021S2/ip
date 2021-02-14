package duke.commands;

import duke.parser.WrongArgumentException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    public ListCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
    }

    public Command process() {
        try {
            if (this.getUserInput().length != 1) {
                throw new WrongArgumentException("Oops, list "
                        + "does not come with a description");
            }
            return new ListCommand(this.getTaskList());
        } catch (WrongArgumentException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    public TaskList execute() {
        return this.getTaskList();
    }

    public String toString() {
        String message = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = this.getTaskList().getList();
        int count = 1;
        for (Task t : tasks) {
            if (count == tasks.size()) {
                message += "     " + count + "." + t.toString();
            } else {
                message += "     " + count + "." + t.toString() + "\n";
            }
            count++;
        }
        return message;
    }
}
