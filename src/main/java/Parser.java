import java.io.IOException;

public class Parser {
    private static final String EXIT_COMMAND = "bye";

    public boolean isExit(String input) {
        return input.equals(EXIT_COMMAND);
    }

    public String execute(String input, TaskList tasks, Storage storage) throws IOException {
        if (input.equals(EXIT_COMMAND)) {
            // close program
            storage.storeTasks(tasks);
            return Ui.sayGoodbye();
        } else if (input.equals("list")) {
            // show everything in the list
            return tasks.iterateList();
        } else if (input.equals("help")){
            return Ui.getHelp();
        } else if (input.split(" ", 2)[0].equals("done")) {
            // mark task with the given index as completed
            return tasks.markAsDone(input.split(" ", 2)[1]);
        } else if (input.split(" ", 2)[0].equals("delete")) {
            return tasks.deleteTask(input.split(" ", 2)[1]);
        } else if (input.split(" ", 2)[0].equals("find")) {
            return tasks.searchTask(input.split(" ", 2)[1]);
        } else {
            // add new task to list
            return tasks.addTask(input);
        }
    }

}
