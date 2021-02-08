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

/**
 * Chatbot Duke created from the Duke Project Template, a greenfield Java Project.
 */
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

    /**
     * Default constructor to be passed to Main, which will output based on the methods called by Duke.
     */
    public Duke() {
        dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
        validator = new DateValidatorLocalDate(dateFormatter);
        ui = new ConsoleUI(System.in);
        parser = new Parser(ui, validator);
        tasks = new Task[100];
        storage = new Storage("./src/main/java/duke/data/taskList.txt");
        taskIterator = storage.readTaskListToArray(tasks, validator);
    }

    /**
     * Outputs the correct results given user input.
     * @param input User input.
     * @return Output in string after processing by Duke.
     */
    public String getResponse(String input) {
        Tuple2 results = parser.parseInput(input, tasks, taskIterator);
        taskIterator = (int) results.getInteger();
        storage.writeTasks(tasks);

        return (String) results.getString();
    }
}
