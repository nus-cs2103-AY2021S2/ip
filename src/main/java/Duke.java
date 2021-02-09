import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

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
/*
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.printIntro();
        Scanner scan = new Scanner(System.in);

        Storage storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");
        Parser parser = new Parser();
        TaskList taskList = new TaskList(storage, ui);

        try {
            storage.loadFileContents(taskList.tasks);
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
                    taskList.listTask();
                    break;
                case "done":
                    taskIndex = Integer.parseInt(result.get(1));
                    taskList.doneTask(taskIndex);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(result.get(1));
                    taskList.deleteTask(taskIndex);
                    break;
                case "todo":
                    description = result.get(1);
                    taskList.addTodo(description);
                    break;
                case "deadline":
                    description = result.get(1);
                    date = result.get(2);
                    taskList.addDeadline(description, date);
                    break;
                case "event":
                    description = result.get(1);
                    date = result.get(2);
                    taskList.addEvent(description, date);
                    break;
                case "find":
                    description = result.get(1);
                    taskList.findTasks(description);
                    break;
                default:
                    ui.printIdkError();
                    break;
            }
            ui.printLine();
        }
    }
*/
    public void prepare() throws IOException {
        try {
            storage.loadFileContents(taskList.tasks);
        } catch (FileNotFoundException e) {
            storage.createFile();
        }
    }

    public String getResponse(String input) throws IOException {

//        String response;

        ArrayList<String> result = parser.parseInputToList(input);
        String command = result.get(0);

        String description, date;
        int taskIndex;

        switch (command) {
        case "bye":
            return ui.printBye();
//            break;
        case "list":
            return taskList.listTask();
//            break;
        case "done":
            taskIndex = Integer.parseInt(result.get(1));
            return taskList.doneTask(taskIndex);
//            break;
        case "delete":
            taskIndex = Integer.parseInt(result.get(1));
            return taskList.deleteTask(taskIndex);
//            break;
        case "todo":
            description = result.get(1);
            return taskList.addTodo(description);
//            break;
        case "deadline":
            description = result.get(1);
            date = result.get(2);
            return taskList.addDeadline(description, date);
//            break;
        case "event":
            description = result.get(1);
            date = result.get(2);
            return taskList.addEvent(description, date);
//            break;
        case "find":
            description = result.get(1);
            return taskList.findTasks(description);
//            break;
        default:
            return command;
//            return ui.printIdkError();
//            break;
        }

//        return "I heard you say: " + input;
    }

}
