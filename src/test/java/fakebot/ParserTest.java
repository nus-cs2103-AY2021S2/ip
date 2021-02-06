package fakebot;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import fakebot.command.Command;
import org.junit.jupiter.api.Test;

import fakebot.task.Deadline;
import fakebot.task.Event;
import fakebot.task.Task;
import fakebot.task.Todo;


class ParserTest {

    private String endDateString = "2011-10-01";
    private String endTimeString = "04:04";
    private String taskName = "Test";
    private String startDateString = "2000-10-01";
    private String startTimeString = "01:04";

    private String dateString = "2000-10-01";
    private String timeString = "01:04";

    @Test
    public void parseTodoGetTaskName_equal() {
        String taskName = "Test";
        Todo todo = new Todo(taskName);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(todo));
        todo = (Todo) task;
        assertEquals(taskName, todo.getTaskName(), "Task Name does not match");
    }

    @Test
    public void parseTodoMarkComplete_success() {
        Todo todo = new Todo("Test");
        todo.markComplete();
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(todo));
        todo = (Todo) task;
        assertEquals(true, todo.isComplete(), "Task not completed");
    }



    @Test
    public void parseEventGetTaskName_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(taskName, event.getTaskName(), "Task Name does not match");
    }

    @Test
    public void parseEventMarkComplete_success() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        event.markComplete();
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(true, event.isComplete(), "Task not completed");
    }

    @Test
    public void parseEventGetStartDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(startDateString, event.getStartDate(), "Start Date does not match");
    }

    @Test
    public void parseEventGetStartTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(startTimeString, event.getStartTime(), "Start Time does not match");
    }

    @Test
    public void parseEventGetEndDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(endDateString, event.getEndDate(), "End Date does not match");
    }

    @Test
    public void parseEventGetEndTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(event));
        event = (Event) task;
        assertEquals(endTimeString, event.getEndTime(), "End Time does not match");
    }

    @Test
    public void parseDeadlineGetTaskName_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadline deadline = new Deadline(taskName, date, time);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(deadline));
        deadline = (Deadline) task;
        assertEquals(taskName, deadline.getTaskName(), "Task Name does not match");
    }

    @Test
    public void parseDeadlineMarkComplete_success() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadline deadline = new Deadline(taskName, date, time);
        deadline.markComplete();
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(deadline));
        deadline = (Deadline) task;
        assertEquals(true, deadline.isComplete(), "Task not completed");
    }

    @Test
    public void parseDeadlineGetDeadlineDate_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadline deadline = new Deadline(taskName, date, time);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(deadline));
        deadline = (Deadline) task;
        assertEquals(dateString, deadline.getDeadlineDate(), "Deadline Date does not match");
    }

    @Test
    public void parseDeadlineGetDeadlineTime_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadline deadline = new Deadline(taskName, date, time);
        Task task = Parser.convertStringToTask(Parser.convertTaskToString(deadline));
        deadline = (Deadline) task;
        assertEquals(timeString, deadline.getDeadlineTime(), "Deadline Time does not match");
    }

    @Test
    void parseUserInputToCommand_noError() {
        String[] userInputs = {
                "todo test",
                "deadline test /by 2011-01-10 01:01",
                "event test /at 2011-01-10 02:01 2012-01-10 02:03",
                "done 1",
                "delete 1",
                "find test",
                "list",
                "bye"
        };

        FakeBot fakeBot = new FakeBot("","");
        try {
            for (String input : userInputs) {
                Command command = Parser.parseUserInputToCommand(input);
                String outputText = fakeBot.processCommand(command);
            }
        }catch (Exception e) {
            assertTrue(false, e.getMessage());
        }
    }
}
