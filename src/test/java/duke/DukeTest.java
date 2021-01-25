package duke;

import main.java.duke.*;
import main.java.duke.command.Command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    private static final String filePath = "data/task.txt";

    @BeforeEach
    public void init() {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.storage.clearFile();
        this.tasks = new TaskList(this.storage.load(), this.storage);
        this.ui = new Ui("Olly");
    }

    @Test
    public void addTodo_todoTest_success() throws DukeException {
        Command c = this.parser.parse("todo test");
        c.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertEquals("[T]✘ test", tasks.find(0).toString());
        assertFalse(tasks.find(0).getStatus());
    }

    @Test
    public void emptyCommand_blankInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void deleteTodo_todoTest_success() throws DukeException {
        Command c = this.parser.parse("todo test");
        c.execute(tasks, ui, storage);
        c = this.parser.parse("delete 1");
        c.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertEquals("", tasks.toString());
    }

    @Test
    public void doneTodo_todoTest_success() throws DukeException {
        Command c = this.parser.parse("todo test");
        c.execute(tasks, ui, storage);
        c = this.parser.parse("done 1");
        c.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.find(0).getStatus());
    }

    @Test
    public void todo_noName_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("todo");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void deadline_noName_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void deadline_noDate_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline assignment0");
            c.execute(tasks, ui, storage);
        });

        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline assignment0 /by");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void deadline_wrongSyntax_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline assignment0");
            c.execute(tasks, ui, storage);
        });

        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline assignment0 /at");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void deadline_wrongDateFormat_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("deadline assignment0 /by 01-25-2021");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void event_noName_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void event_noDate_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event hackathon");
            c.execute(tasks, ui, storage);
        });

        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event hackathon /at");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void event_wrongSyntax_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event hackathon");
            c.execute(tasks, ui, storage);
        });

        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event hackathon /by");
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void event_wrongDateFormat_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Command c = this.parser.parse("event hackathon /at 01-25-2021");
            c.execute(tasks, ui, storage);
        });
    }

    @AfterEach
    public void teardown() {
        tasks.deleteAll();
    }
}