import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testGetSize() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        assertEquals(3, tasks.getSize());
    }

    @Test
    public void testGetTask() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        assertEquals(t1, tasks.getTask(1));
        assertEquals(t2, tasks.getTask(2));
        assertEquals(t3, tasks.getTask(3));
    }

    @Test
    public void testJoinToTxt() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        assertEquals(System.lineSeparator() + "T | 0 | join sports club"
                + System.lineSeparator() + "D | 1 | return book | 2019-09-09"
                + System.lineSeparator() + "E | 0 | meeting | 2020-01-01" , tasks.joinToTxt());
    }

    @Test
    public void testStringConversion() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        assertEquals("  1. [T][ ] join sports club\n"
                + "  2. [D][\u2713] return book (By: 9 Sep 2019)\n"
                + "  3. [E][ ] meeting (At: 1 Jan 2020)", tasks.toString());

    }
}
