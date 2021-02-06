package fakebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;


class EventTest {
    private String taskName = "Test";
    private String startDateString = "2000-10-01";
    private String startTimeString = "01:04";
    private String endDateString = "2011-10-01";
    private String endTimeString = "04:04";

    @Test
    public void getTaskName_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        assertEquals(taskName, event.getTaskName(), "Task Name does not match");
    }

    @Test
    public void markComplete_success() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        event.markComplete();
        assertEquals(true, event.isComplete(), "Task not completed");
    }

    @Test
    public void getStartDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        assertEquals(startDateString, event.getStartDate(), "Start Date does not match");
    }

    @Test
    public void getStartTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        assertEquals(startTimeString, event.getStartTime(), "Start Time does not match");
    }

    @Test
    public void getEndDate_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        assertEquals(endDateString, event.getEndDate(), "End Date does not match");
    }

    @Test
    public void getEndTime_equal() {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDate endDate = LocalDate.parse(endDateString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        Event event = new Event(taskName, startDate, startTime, endDate, endTime);
        assertEquals(endTimeString, event.getEndTime(), "End Time does not match");
    }
}
