import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents main class for chat bot.
 */
public class Duke {

    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.printIntro();
        Scanner scan = new Scanner(System.in);

        Storage storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");
        Parser parser = new Parser();
        TaskList tasklist = new TaskList(storage, ui);

        try {
            storage.loadFileContents(tasklist.tasks);
        } catch (FileNotFoundException e) {
            storage.createFile();
        }

        loop:
        while (scan.hasNextLine()) {

            ui.printLine();

            ArrayList<String> result = parser.parseInputToList(scan);
            String command = result.get(0);

            String description, date;
            int taskIndex;

            switch (command) {
                case "bye":
                    ui.printBye();
                    break loop;
                case "list":
                    tasklist.listTask();
                    break;
                case "done":
                    taskIndex = Integer.parseInt(result.get(1));
                    tasklist.doneTask(taskIndex);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(result.get(1));
                    tasklist.deleteTask(taskIndex);
                    break;
                case "todo":
                    description = result.get(1);
                    tasklist.addTodo(description);
                    break;
                case "deadline":
                    description = result.get(1);
                    date = result.get(2);
                    tasklist.addDeadline(description, date);
                    break;
                case "event":
                    description = result.get(1);
                    date = result.get(2);
                    tasklist.addEvent(description, date);
                    break;
                default:
                    ui.printIdkError();
                    break;
            }
            ui.printLine();
        }
    }
}
