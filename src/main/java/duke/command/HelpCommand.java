package duke.command;

import duke.TaskList;

public class HelpCommand implements Command {
    @Override
    public boolean shouldExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return "\nHere are a list of commands you can tell me:\n"
                + "\n'list'"
                + "\nto list the current tasks on your list"
                + "\n'todo <description>'"
                + "\nto add a basic ToDo duke.task to your list"
                + "\n'deadline <description> /by YYYY-MM-DD'"
                + "\nto add a Deadline duke.task with date specified"
                + "\n'event <description> /at YYYY-MM-DD AAAA-BBBB'"
                + "\nto add an Event duke.task from time AAAA to BBBB"
                + "\n'delete <duke.task number>'"
                + "\nto delete duke.task from your list"
                + "\n'bye'"
                + "\nto exit the program";
    }

    @Override
    public TaskList execute(TaskList taskList) {
        return taskList;
    }
}
