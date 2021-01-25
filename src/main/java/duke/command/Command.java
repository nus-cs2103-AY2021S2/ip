package duke.command;

import duke.task.TaskList;

public interface Command {
    String execute(TaskList taskList);
}
