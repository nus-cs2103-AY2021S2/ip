package yoda.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.ToDo;

class AddCommandTest {
    @Test
    void testMakeToDo() throws InvalidCommandFormatException, InvalidDateTimeFormatException {
        String[] toDoArgs = {"TODO", "read", "a", "book"};
        AddCommand addToDo = new AddCommand(toDoArgs);
        ToDo toDo = new ToDo("read a book ");
        assertEquals(addToDo.makeTask().toString(), toDo.toString());
    }

    @Test
    void testMakeEvent() throws InvalidCommandFormatException, InvalidDateTimeFormatException {
        String[] eventArgs = {"EVENT", "attend", "meeting", "/at", "2021-09-09", "0900"};
        AddCommand addEvent = new AddCommand(eventArgs);
        Event event = new Event("attend meeting ", "2021-09-09 0900");
        assertEquals(addEvent.makeTask().toString(), event.toString());
    }

    @Test
    void testMakeDeadline() throws InvalidCommandFormatException, InvalidDateTimeFormatException {
        String[] deadlineArgs = {"DEADLINE", "finish", "quiz", "/by", "2021-09-09", "0900"};
        AddCommand addDeadline = new AddCommand(deadlineArgs);
        Deadline deadline = new Deadline("finish quiz ", "2021-09-09 0900");
        assertEquals(addDeadline.makeTask().toString(), deadline.toString());
    }

    @Test
    void testCheckDateTime() throws InvalidDateTimeFormatException {
        String[] toDoArgs = {"TODO", "read", "a", "book"};
        AddCommand addToDo = new AddCommand(toDoArgs);
        String date = "2021-10-10";
        String time = "1300";
        assertEquals("2021-10-10 1300", addToDo.checkDateTime(date, time));
    }
}
