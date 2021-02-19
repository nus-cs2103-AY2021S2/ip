package duke.commands;

import duke.parser.InsufficientArgumentsException;
import duke.tasks.TaskList;

/**
 * FindCommand is a command that finds if a task exists within the task list.
 */
public class FindCommand extends Command {
    private String taskDescription;

    public FindCommand(TaskList taskList, String taskDescription) {
        super(taskList);
        this.taskDescription = taskDescription;
    }

    public FindCommand(String[] userInput, TaskList taskList) {
        super(userInput, taskList);
        this.taskDescription = "";
    }

    @Override
    public Command process() {
        try {
            if (getUserInput().length == 1) {
                throw new InsufficientArgumentsException("OOPS!!! The "
                        + "description of find cannot be empty.");
            }
            String toFind = "";
            for (int i = 1; i < getUserInput().length; ++i) {
                if (i != this.getUserInput().length - 1) {
                    toFind += this.getUserInput()[i] + " ";
                } else {
                    toFind += this.getUserInput()[i];
                }
            }
            return new FindCommand(this.getTaskList(), toFind);
        } catch (InsufficientArgumentsException e) {
            return new ErrorCommand(this.getTaskList(), e.getMessage());
        }
    }

    @Override
    public TaskList execute() {
        return this.getTaskList();
    }

    @Override
    public String toString() {
        String message = "Here are the matching tasks in your list:\n";
        TaskList tasks = this.getTaskList();
        boolean first = true;
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(this.taskDescription)) {
                if (first) {
                    message += (i + 1) + "." + tasks.get(i).toString();
                    first = false;
                } else {
                    message += "\n" + (i + 1) + "." + tasks.get(i).toString();
                }
            }
        }
        if (first) {
            message = "There are no matching tasks";
        }
        return message;
    }
}