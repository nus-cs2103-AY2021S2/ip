package duke.commands;

import duke.exceptions.DateTimeNotFoundException;
import duke.exceptions.DescriptionNotFoundException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommandTest {

    @Test
    public void testAddCommand_noDescriptionGiven_throwsDescriptionNotFoundException() {
        String testCommand = "deadline /by";
        String[] splitTestCommand = testCommand.split(" ");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand, splitTestCommand);
        assertThrows(DescriptionNotFoundException.class, () -> addCommand.execute(tasks, ui, storage));
    }

    @Test
    public void testAddCommand_noDateGiven_throwsDateTimeNotFoundException() {
        String testCommand = "deadline testing /by";
        String[] splitTestCommand = testCommand.split(" ");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand, splitTestCommand);
        assertThrows(DateTimeNotFoundException.class, () -> addCommand.execute(tasks, ui, storage));
    }

    @Test
    public void testAddCommand_invalidDateTimeFormatGiven_throwsDateTimeParseException() {
        String testCommand = "deadline testing /by 20/20/2020";
        String[] splitTestCommand = testCommand.split(" ");
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand, splitTestCommand);
        assertThrows(DateTimeParseException.class, () -> addCommand.execute(tasks, ui, storage));
    }
}
