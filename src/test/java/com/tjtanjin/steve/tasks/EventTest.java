package com.tjtanjin.steve.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    LocalDate[] taskDates = new LocalDate[] {LocalDate.parse("2020-04-05"), LocalDate.parse("2020-04-06")};
    private final Task TASK = new Event("Test Event", "incomplete", taskDates);

    @Test
    void getTaskName_whenInvoke_thenOutputTaskName() {
        String expected = "Test Event";
        String taskName = TASK.getTaskName();
        assertEquals(expected, taskName);
    }

    @Test
    void getStatus_whenInvoke_thenOutputTaskStatus() {
        String expected = "incomplete";
        String taskStatus = TASK.getStatus();
        assertEquals(expected, taskStatus);
    }

    @Test
    void getType_whenInvoke_thenOutputTaskType() {
        String expected = "EVENT";
        String taskType = TASK.getType();
        assertEquals(expected, taskType);
    }

    @Test
    void getDate_whenInvoke_thenOutputTaskDate() {
        LocalDate[] expected = new LocalDate[] {LocalDate.parse("2020-04-05"), LocalDate.parse("2020-04-06")};
        LocalDate[] taskDates = TASK.getDates();
        assertEquals(expected[0], taskDates[0]);
        assertEquals(expected[1], taskDates[1]);
    }

    @Test
    void markCompleted_whenInvoke_thenMarkTaskComplete() {
        String expected = "complete";
        Task task = TASK.markCompleted();
        String taskStatus = task.getStatus();
        assertEquals(expected, taskStatus);
    }
}
