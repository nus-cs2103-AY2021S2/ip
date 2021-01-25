package duke.command;

import duke.DukeException;
import duke.StorageStub;
import duke.TaskList;
import duke.Ui;

import duke.task.DeadlineTask;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        DeadlineCommand dlCmd = new DeadlineCommand("deadline testWronglyFormatted DeadlineCmd /at 2020-01-01 1800", new Ui());
        assertThrows(DukeException.class, () -> {
            dlCmd.run(new StorageStub(), new TaskList());
        });
    }

    @Test
    public void run_wronglyFormattedDeadlineCommand3_exceptionThrown() {
        DeadlineCommand dlCmd = new DeadlineCommand("deadline testWronglyFormatted DeadlineCmd /by 2020-01-01 1870", new Ui());
        assertThrows(DukeException.class, () -> {
            dlCmd.run(new StorageStub(), new TaskList());
        });
    }

    @Test
    public void run_correctlyFormattedDeadlineCommand_exceptionThrown() throws DukeException, IOException {
        DeadlineCommand dlCmd = new DeadlineCommand("deadline test correctly formatted DeadlineCmd /by 2020-01-01 1800", new Ui());
        TaskList tl = new TaskList();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ldt = LocalDateTime.parse("2020-01-01 1800", dtf);
        DeadlineTask dlTask = new DeadlineTask("test correctly formatted DeadlineCmd already in list", ldt);
        tl.add(dlTask); // tasklist now holds only 1 deadline command
        dlCmd.run(new StorageStub(), tl); // adding another deadline command to tasklist
        assertEquals(2, tl.getSize());
    }
}
