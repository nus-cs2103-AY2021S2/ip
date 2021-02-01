package fakebot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlinesTest {
    String taskName = "Test";
    String dateString = "2000-10-01";
    String timeString = "01:04";
    @Test
    public void getTaskName_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        assertEquals(taskName, deadline.getTaskName(),"Task Name does not match");
    }

    @Test
    public void markComplete_success() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        deadline.markComplete();
        assertEquals(true, deadline.isComplete(), "Task not completed");
    }

    @Test
    public void getDeadlineDate_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        assertEquals(dateString, deadline.getDeadlineDate(),"Deadline Date does not match");
    }

    @Test
    public void getDeadlineTime_equal() {
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        Deadlines deadline = new Deadlines(taskName, date, time);
        assertEquals(timeString, deadline.getDeadlineTime(),"Deadline Time does not match");
    }
}