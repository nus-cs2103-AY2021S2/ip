package duke;

import java.util.Locale;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.ConsoleUI;
import duke.taskclass.*;
import duke.datevalidator.DateValidator;
import duke.datevalidator.DateValidatorLocalDate;

public class Duke {

    public static void main(String[] args) {
            /** Create validator for dates */
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
            DateValidator validator = new DateValidatorLocalDate(dateFormatter);
            /** Handler for all UI stuff. */
            ConsoleUI ui = new ConsoleUI(System.in);
            ui.introduction();
            /** Parser for user inputs */
            Parser parser = new Parser(ui, validator);
            /** Create tasks */
            Task[] tasks = new Task[100];
            /** Initialize file IO */
            // System.out.println("Working Directory = " + System.getProperty("user.dir"));
            Storage storage = new Storage("./src/main/java/duke/data/taskList.txt");
            int taskIterator = storage.readTaskListToArray(tasks, validator);
            System.out.println("Num tasks: " + taskIterator);

            while (parser.getIsBye() == false) {
                taskIterator = parser.parseInput(tasks, taskIterator);
            }
            storage.writeTasks(tasks);
            ui.bye();
    }
}
