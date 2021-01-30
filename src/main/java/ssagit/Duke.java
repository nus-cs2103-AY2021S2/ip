package ssagit;

import java.util.Locale;
import java.time.format.DateTimeFormatter;

import ssagit.parser.Parser;
import ssagit.storage.Storage;
import ssagit.ui.ConsoleUI;
import ssagit.taskclass.*;
import ssagit.datevalidator.DateValidator;
import ssagit.datevalidator.DateValidatorLocalDate;

public class Duke {

    public static void main(String[] args) {
            /** Handler for all UI stuff. */
            ConsoleUI ui = new ConsoleUI(System.in);
            ui.introduction();
            /** Parser for user inputs */
            Parser parser = new Parser(ui);
            /** Create tasks */
            Task[] tasks = new Task[100];
            /** Create validator for dates */
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
            DateValidator validator = new DateValidatorLocalDate(dateFormatter);
            /** Initialize file IO */
            Storage storage = new Storage("./ssagit/data/taskList.txt");
            int taskIterator = storage.readTaskListToArray(tasks, validator);

            while (parser.getIsBye() == false) {
                parser.parseInput(tasks, validator, taskIterator);
            }
            storage.writeTasks(tasks);
            ui.bye();
    }
}
