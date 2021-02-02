import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void setAsDone_invalidInput_argumentExceptionThrown() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        Parser parser = new Parser(tasks);

        assertThrows(ArgumentException.class, () -> parser.setAsDone("done "));
        assertThrows(ArgumentException.class, () -> parser.setAsDone("done a"));
        assertThrows(ArgumentException.class, () -> parser.setAsDone("done 0"));
        assertThrows(ArgumentException.class, () -> parser.setAsDone("done -1"));
        assertThrows(ArgumentException.class, () -> parser.setAsDone("done 6"));
    }

    @Test
    public void deleteFromList_invalidInput_argumentExceptionThrown() throws DukeException {
        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        Parser parser = new Parser(tasks);

        assertThrows(ArgumentException.class, () -> parser.deleteFromList("delete "));
        assertThrows(ArgumentException.class, () -> parser.deleteFromList("delete a"));
        assertThrows(ArgumentException.class, () -> parser.deleteFromList("delete 0"));
        assertThrows(ArgumentException.class, () -> parser.deleteFromList("delete -1"));
        assertThrows(ArgumentException.class, () -> parser.deleteFromList("delete 6"));
    }

    @Test
    public void addTaskToList_invalidTodo_argumentExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertThrows(ArgumentException.class, () -> parser.addTaskToList("todo ", false));
    }

    @Test
    public void addTaskToList_invalidDeadline_argumentExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline /by ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline /by jan", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline return book /by", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline return book /by ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline /by 2019-09-09", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("deadline /by2019-09-09", false));
    }

    @Test
    public void addTaskToList_invalidEvent_argumentExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event /at ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event /at jan", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event return book /at", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event return book /at ", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event /at 2019-09-09", false));
        assertThrows(ArgumentException.class, () -> parser.addTaskToList("event /at2019-09-09", false));
    }

    @Test
    public void addTaskToList_unknownKeyword_argumentExceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertThrows(KeywordException.class, () -> parser.addTaskToList("", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList(" ", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("s", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("1", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("hi", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("todo", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("deadline", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("event", false));
        assertThrows(KeywordException.class, () -> parser.addTaskToList("Event ", false));
    }

}
