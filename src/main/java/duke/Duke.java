package duke;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.datevalidator.DateValidator;
import duke.datevalidator.DateValidatorLocalDate;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskclass.Task;
import duke.ui.ConsoleUI;

public class Duke {
    /**
     * Chatbot Duke created from the Duke Project Template, a greenfield Java Project
     */
    public static void main() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
        DateValidator validator = new DateValidatorLocalDate(dateFormatter);
        ConsoleUI ui = new ConsoleUI(System.in);
        ui.introduction();
        Parser parser = new Parser(ui, validator);
        Task[] tasks = new Task[100];
        Storage storage = new Storage("./src/main/java/duke/data/taskList.txt");
        int taskIterator = storage.readTaskListToArray(tasks, validator);

        while (parser.getIsBye() == false) {
            taskIterator = parser.parseInput(tasks, taskIterator);
        }
        storage.writeTasks(tasks);
        ui.bye();
    }
}
