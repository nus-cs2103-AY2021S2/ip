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
                + "\nto add a basic ToDo task to your list"
                + "\n'deadline <description> /by YYYY-MM-DD'"
                + "\nto add a Deadline task with date specified"
                + "\n'event <description> /at YYYY-MM-DD AAAA-BBBB'"
                + "\nto add an Event task from time AAAA to BBBB"
                + "\n'delete <task number>'"
                + "\nto delete task from your list"
                + "\n'bye'"
                + "\nto exit the program";
    }

    @Override
    public TaskList execute(TaskList taskList) {
        return taskList;
    }
}
