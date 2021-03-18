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

        final String EMPTY_DESC_ERROR = "emptyDescError";
        final String BLANK = "";

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
        case "edit":
            taskIndex = Integer.parseInt(result.get(1));
            boolean hasDescription, hasDate;
            hasDescription = Integer.parseInt(result.get(2)) == 1;
            description = result.get(3);
            hasDate = Integer.parseInt(result.get(4)) == 1;
            date = result.get(5);
            return taskList.editTask(taskIndex, hasDescription, hasDate, description, date);
        case "todo":
            assert(result.get(1) != null);
            description = result.get(1);
            return taskList.addTodo(description);
        case "deadline":
            assert(result.get(1) != null);
            description = result.get(1);

            if (result.get(1).equals(EMPTY_DESC_ERROR)) {
                date = BLANK;
            } else {
                assert(result.get(2) != null);
                date = result.get(2);
            }
            return taskList.addDeadline(description, date);
        case "event":
            assert(result.get(1) != null);
            description = result.get(1);

            if (result.get(1).equals(EMPTY_DESC_ERROR)) {
                date = BLANK;
            } else {
                assert(result.get(2) != null);
                date = result.get(2);
            }
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
