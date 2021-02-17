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

        String description, date;
        int taskIndex;

        switch (command) {
        case "bye":
            return ui.printBye();
        case "list":
            return taskList.listTask();
        case "done":
            assert(result.get(1) != null);
            taskIndex = Integer.parseInt(result.get(1));
            return taskList.doneTask(taskIndex);
        case "delete":
            assert(result.get(1) != null);
            taskIndex = Integer.parseInt(result.get(1));
            return taskList.deleteTask(taskIndex);
        case "todo":
            assert(result.get(1) != null);
            description = result.get(1);
            return taskList.addTodo(description);
        case "deadline":
            assert(result.get(1) != null && result.get(2) != null);
            description = result.get(1);
            date = result.get(2);
            return taskList.addDeadline(description, date);
        case "event":
            assert(result.get(1) != null && result.get(2) != null);
            description = result.get(1);
            date = result.get(2);
            return taskList.addEvent(description, date);
        case "find":
            assert(result.get(1) != null);
            description = result.get(1);
            return taskList.findTasks(description);
        default:
            return ui.printIdkError();
        }

    }

}
