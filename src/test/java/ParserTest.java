import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void setAsDone_invalidInput_argumentExceptionString() throws DukeException {
        String expectedOutput = "Wrong Arguments Specified: \n"
            + "Please enter a valid item number in the list.";

        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.setAsDone("done "));
        assertEquals(expectedOutput, parser.setAsDone("done a"));
        assertEquals(expectedOutput, parser.setAsDone("done 0"));
        assertEquals(expectedOutput, parser.setAsDone("done -1"));
        assertEquals(expectedOutput, parser.setAsDone("done 6"));
    }

    @Test
    public void deleteFromList_invalidInput_argumentExceptionString() throws DukeException {
        String expectedOutput = "Wrong Arguments Specified: \n"
            + "Please enter a valid item number in the list.";

        TaskList tasks = new TaskList();

        Task t1 = new Todo("join sports club", false);
        Task t2 = new Deadline("return book", true, "2019-09-09");
        Task t3 = new Event("meeting", false, "2020-01-01");

        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);

        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.setAsDone("delete "));
        assertEquals(expectedOutput, parser.setAsDone("delete a"));
        assertEquals(expectedOutput, parser.setAsDone("delete 0"));
        assertEquals(expectedOutput, parser.setAsDone("delete -1"));
        assertEquals(expectedOutput, parser.setAsDone("delete 6"));
    }

    @Test
    public void addTaskToList_invalidTodo_argumentExceptionString() {
        String expectedOutput = "Wrong Arguments Specified: \n"
            + "A todo-task should be specified as follows \n "
            + "todo <task_description>\n"
            + "You currently have 0 tasks in the list.";

        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.addTaskToList("todo ", false));
    }

    @Test
    public void addTaskToList_invalidDeadline_argumentExceptionString() {
        String expectedOutput = "Wrong Arguments Specified: \n"
            + "A deadline-task should be specified as follows \n "
            + "deadline <task_description> /by <task_deadline>\n"
            + "You currently have 0 tasks in the list.";

        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.addTaskToList("deadline ", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline /by ", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline /by jan", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline return book /by", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline return book /by ", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline /by 2019-09-09", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline /by2019-09-09", false));
    }

    @Test
    public void addTaskToList_invalidEvent_argumentExceptionString() {
        String expectedOutput = "Wrong Arguments Specified: \n"
            + "A event-task should be specified as follows \n "
            + "event <event_description> /at <event_date>\n"
            + "You currently have 0 tasks in the list.";

        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.addTaskToList("event ", false));
        assertEquals(expectedOutput, parser.addTaskToList("event /at ", false));
        assertEquals(expectedOutput, parser.addTaskToList("event /at jan", false));
        assertEquals(expectedOutput, parser.addTaskToList("event return book /at", false));
        assertEquals(expectedOutput, parser.addTaskToList("event return book /at ", false));
        assertEquals(expectedOutput, parser.addTaskToList("event /at 2019-09-09", false));
        assertEquals(expectedOutput, parser.addTaskToList("event /at2019-09-09", false));
    }

    @Test
    public void addTaskToList_unknownKeyword_argumentExceptionString() {
        String expectedOutput = "Unidentified Keyword: Please try again using the correct keywords and order :)\n"
            + "You currently have 0 tasks in the list.";

        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertEquals(expectedOutput, parser.addTaskToList("", false));
        assertEquals(expectedOutput, parser.addTaskToList(" ", false));
        assertEquals(expectedOutput, parser.addTaskToList("s", false));
        assertEquals(expectedOutput, parser.addTaskToList("1", false));
        assertEquals(expectedOutput, parser.addTaskToList("hi", false));
        assertEquals(expectedOutput, parser.addTaskToList("todo", false));
        assertEquals(expectedOutput, parser.addTaskToList("deadline", false));
        assertEquals(expectedOutput, parser.addTaskToList("event", false));
        assertEquals(expectedOutput, parser.addTaskToList("Event ", false));
    }

}
