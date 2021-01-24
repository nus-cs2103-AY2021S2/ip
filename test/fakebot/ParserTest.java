package fakebot;

import fakebot.command.Command;
import fakebot.task.Deadlines;
import fakebot.task.Events;
import fakebot.task.Task;
import fakebot.task.ToDos;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void parseTodoGetTaskName_equal() {
        String taskName = "Test";
        ToDos todo = new ToDos(taskName);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(todo));
        todo = (ToDos) task;
        assertEquals(taskName, todo.getTaskName(),"Task Name does not match");
    }
    @Test
    public void parseTodoMarkComplete_success() {
        ToDos todo = new ToDos("Test");
        todo.markComplete();
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(todo));
        todo = (ToDos) task;
        assertEquals(true, todo.isComplete(), "Task not completed");
    }


    String taskName = "Test";
    String startDateString = "2000-10-01";
    String startTimeString = "01:04";
    String endDateString = "2011-10-01";
    String endTimeString = "04:04";
    @Test
    public void parseEventGetTaskName_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(taskName, event.getTaskName(),"Task Name does not match");
    }
    @Test
    public void parseEventMarkComplete_success() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        event.markComplete();
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(true, event.isComplete(), "Task not completed");
    }

    @Test
    public void parseEventGetStartDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(startDateString, event.getStartDate(),"Start Date does not match");
    }

    @Test
    public void parseEventGetStartTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(startTimeString, event.getStartTime(),"Start Time does not match");
    }

    @Test
    public void parseEventGetEndDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(endDateString, event.getEndDate(),"End Date does not match");
    }

    @Test
    public void parseEventGetEndTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Events event = new Events(taskName, startDate, startTime, endDate, endTime);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(event));
        event = (Events) task;
        assertEquals(endTimeString, event.getEndTime(),"End Time does not match");
    }


    String dateString = "2000-10-01";
    String timeString = "01:04";
    @Test
    public void parseDeadlineGetTaskName_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(deadline));
        deadline = (Deadlines) task;
        assertEquals(taskName, deadline.getTaskName(),"Task Name does not match");
    }

    @Test
    public void parseDeadlineMarkComplete_success() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        deadline.markComplete();
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(deadline));
        deadline = (Deadlines) task;
        assertEquals(true, deadline.isComplete(), "Task not completed");
    }

    @Test
    public void parseDeadlineGetDeadlineDate_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(deadline));
        deadline = (Deadlines) task;
        assertEquals(dateString, deadline.getDeadlineDate(),"Deadline Date does not match");
    }

    @Test
    public void parseDeadlineGetDeadlineTime_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        Task task = Parser.parseStringToTask(Parser.convertTaskTOString(deadline));
        deadline = (Deadlines) task;
        assertEquals(timeString, deadline.getDeadlineTime(),"Deadline Time does not match");
    }
}