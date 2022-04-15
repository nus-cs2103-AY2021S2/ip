import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * Represents main class for chat bot.
 */
public class Duke {
    Ui ui = new Ui();
    Parser parser = new Parser();
    Storage storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");
    TaskList taskList = new TaskList(storage, ui);

    public Duke() {
    }

    public void prepare() throws IOException {
        try {
            storage.loadFileContents(taskList.tasks);
        } catch (FileNotFoundException e) {
            storage.createFile();
        }
    }

    public String getResponse(String input) throws IOException {
        assert(!input.equals(""));

        ArrayList<String> result = parser.parseInputToList(input);
        assert(result.get(0) != null);

        String command = result.get(0);

        String firstArg, date;
        int taskIndex;

        final String EMPTY_DESC_ERROR = "emptyDescError";

        switch (command) {
        case "bye":
            return ui.printBye();
        case "list":
            return taskList.listTask();
        default:
            assert (result.get(1) != null);
            if (result.get(1).equals(EMPTY_DESC_ERROR)) {
                return taskList.printEmptyDescError(command);
            } else {
                firstArg = result.get(1);
                switch (command) {
                case "done":
                    return taskList.doneTask(firstArg);
                case "delete":
                    return taskList.deleteTask(firstArg);
                case "edit":
                    boolean hasDescription, hasDate;
                    hasDescription = Integer.parseInt(result.get(2)) == 1;
                    String description = result.get(3);
                    hasDate = Integer.parseInt(result.get(4)) == 1;
                    date = result.get(5);
                    return taskList.editTask(firstArg, hasDescription, hasDate, description, date);
                case "todo":
                    return taskList.addTodo(firstArg);
                case "deadline":
                    assert (result.get(2) != null);
                    date = result.get(2);
                    return taskList.addDeadline(firstArg, date);
                case "event":
                    assert (result.get(2) != null);
                    date = result.get(2);
                    return taskList.addEvent(firstArg, date);
                case "find":
                    return taskList.findTasks(firstArg);
                default:
                    return ui.printIdkError();
                }
            }
        }

    }

}
