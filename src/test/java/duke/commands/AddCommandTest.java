package duke.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exceptions.DateTimeNotFoundException;
import duke.exceptions.DescriptionNotFoundException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommandTest {

    @Test
    public void testAddCommand_noDescriptionGiven_throwsDescriptionNotFoundException() {
        String testCommand = "deadline /by";
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand);
        assertThrows(DescriptionNotFoundException.class, () -> addCommand.execute(tasks, ui, storage));
    }

    @Test
    public void testAddCommand_noDateGiven_throwsDateTimeNotFoundException() {
        String testCommand = "deadline testing /by";
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand);
        assertThrows(DateTimeNotFoundException.class, () -> addCommand.execute(tasks, ui, storage));
    }

    @Test
    public void testAddCommand_invalidDateTimeFormatGiven_throwsDateTimeParseException() {
        String testCommand = "deadline testing /by 20/20/2020";
        TaskList tasks = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage("AddCommandTest.txt");
        Ui ui = new Ui();
        AddCommand addCommand = new AddCommand(testCommand);
        assertThrows(DateTimeParseException.class, () -> addCommand.execute(tasks, ui, storage));
    }
}
