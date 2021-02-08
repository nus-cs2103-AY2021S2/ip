import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Parser {
    private static final String EXIT_COMMAND = "bye";

    /**
     * Takes in user input and deconstructs the command given by the user.
     *
     * @param input input provided by user on console.
     * @param taskList list of current tasks.
     * @return true if the user has executed an exit command.
     */
    public boolean parse(String input, TaskList taskList) {
        if (input.equals(EXIT_COMMAND)) {
            // close program
            Ui.formatText();
            Ui.sayGoodbye();
            Ui.formatText();
            return true;
        } else if (input.equals("list")) {
            // show everything in the list
            Ui.formatText();
            taskList.iterateList();
            Ui.formatText();
        } else if (input.split(" ", 2)[0].equals("done")) {
            // mark task with the given index as completed
            taskList.markAsDone(input.split(" ", 2)[1]);
        } else if (input.split(" ", 2)[0].equals("delete")) {
            taskList.deleteTask(input.split(" ", 2)[1]);
        } else if (input.split(" ", 2)[0].equals("find")) {
            taskList.searchTask(input.split(" ", 2)[1]);
        }else {
            // add new task to list
            taskList.addTask(input);
        }
        return false;
    }

    public String execute(String input, TaskList tasks, Storage storage) {
        if (input.equals(EXIT_COMMAND)) {
            // close program
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
