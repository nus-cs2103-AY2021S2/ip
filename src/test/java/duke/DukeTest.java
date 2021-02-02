package duke;

import duke.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testSave() {
        TaskList tl = new TaskList();
        tl.add(new Task("list 1"));
        tl.add(new Task("list 2", 'D', LocalDate.now()));
        tl.add(new Task("list 3", 'E', LocalDate.now()));

        Storage st = new Storage("tasklist2");
        st.saveTaskList(tl);

        TaskList newTl = st.loadTaskList();

        assertEquals(true, tl.equals(newTl));
    }

    @Test
    public void testDelete() {
       TaskList tl = new TaskList();
        tl.add(new Task("list 1"));
        tl.add(new Task("list 2", 'D', LocalDate.now()));
        tl.add(new Task("list 3", 'E', LocalDate.now()));

        TaskList newTl = new TaskList();
        newTl.add(new Task("list 1"));
        newTl.add(new Task("list 3", 'E', LocalDate.now()));

        Parser p = new Parser();
        p.process("delete 2", tl);

        assertEquals(true, tl.equals(newTl));

    }

    @Test
    public void testAdd() {
        LocalDate ld = LocalDate.parse("2020-01-05");

        TaskList tl = new TaskList();
        tl.add(new Task("list 1"));
        tl.add(new Task("list 2", 'D', ld));
        tl.add(new Task("list 3", 'E', ld));

        TaskList newTl = new TaskList();

        Parser p = new Parser();
        p.process("todo list 1", newTl);
        p.process("deadline list 2 /by 2020-01-05", newTl);
        p.process("event list 3 /at 2020-01-05", newTl);

        assertEquals(true, tl.equals(newTl));

    }

    @Test
    public void testDone() {
        LocalDate ld = LocalDate.parse("2020-01-05");

        TaskList tl = new TaskList();
        tl.add(new Task("list 1"));
        tl.add(new Task("list 2", 'D', ld));
        tl.add(new Task("list 3", 'E', ld));
        try {
            tl.markDone(2);
        } catch (DukeException de) {

        }

        assertEquals(true, tl.getTask(1).getDone());

    }
}
