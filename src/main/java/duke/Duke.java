package duke;

import duke.datevalidator.DateValidator;
import duke.datevalidator.DateValidatorLocalDate;
import duke.parser.Parser;
import duke.parser.Tuple2;
import duke.storage.Storage;
import duke.taskclass.Task;
import duke.ui.ConsoleUI;
import javafx.scene.image.Image;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Duke {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/you.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    private int taskIterator;
    private DateTimeFormatter dateFormatter;
    private DateValidator validator;
    private ConsoleUI ui;
    private Parser parser;
    private Task[] tasks;
    private Storage storage;

    public Duke() {
        dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
        validator = new DateValidatorLocalDate(dateFormatter);
        ui = new ConsoleUI(System.in);
        // ui.introduction();
        parser = new Parser(ui, validator);
        tasks = new Task[100];
        storage = new Storage("./src/main/java/duke/data/taskList.txt");
        taskIterator = storage.readTaskListToArray(tasks, validator);
    }
    /**
     * Chatbot Duke created from the Duke Project Template, a greenfield Java Project
     */
    /*
    public static void main(String[] args) {
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
    */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Tuple2 results = parser.parseInput(input, tasks, taskIterator);
        taskIterator = (int) results.getInteger();
        storage.writeTasks(tasks);

        return (String) results.getString();
    }
}
