package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.StorageStub;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.DeadlineTask;

public class DeadlineCommandTest {

    @Test
    public void run_wronglyFormattedDeadlineCommand1_exceptionThrown() {
        DeadlineCommand dlCmd = new DeadlineCommand("deadline wronginput", new Ui());
        assertThrows(DukeException.class, () -> {
            dlCmd.run(new StorageStub(), new TaskList());
        });
    }

    @Test
    public void run_wronglyFormattedDeadlineCommand2_exceptionThrown() {
        String testName = "deadline testWronglyFormatted DeadlineCmd /at 2020-01-01 1800";
        DeadlineCommand dlCmd = new DeadlineCommand(testName, new Ui());
        assertThrows(DukeException.class, () -> {
            dlCmd.run(new StorageStub(), new TaskList());
        });
    }

    @Test
    public void run_wronglyFormattedDeadlineCommand3_exceptionThrown() {
        String testName = "deadline testWronglyFormatted DeadlineCmd /by 2020-01-01 1870";
        DeadlineCommand dlCmd = new DeadlineCommand(testName, new Ui());
        assertThrows(DukeException.class, () -> {
            dlCmd.run(new StorageStub(), new TaskList());
        });
    }

    @Test
    public void run_correctlyFormattedDeadlineCommand_exceptionThrown() throws DukeException, IOException {
        String testName = "deadline test correctly formatted DeadlineCmd /by 2020-01-01 1800";
        DeadlineCommand dlCmd = new DeadlineCommand(testName, new Ui());
        TaskList tl = new TaskList();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ldt = LocalDateTime.parse("2020-01-01 1800", dtf);
        DeadlineTask dlTask = new DeadlineTask("test correctly formatted DeadlineCmd already in list", ldt);
        tl.add(dlTask); // tasklist now holds only 1 deadline command
        dlCmd.run(new StorageStub(), tl); // adding another deadline command to tasklist
        assertEquals(2, tl.getSize());
    }
}
