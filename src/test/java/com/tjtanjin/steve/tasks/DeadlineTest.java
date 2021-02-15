package com.tjtanjin.steve.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    LocalDate[] taskDate = new LocalDate[] {LocalDate.parse("2020-04-05")};
    private final Task TASK = new Deadline("Test Deadline", "incomplete", taskDate);

    @Test
    void getTaskName_whenInvoke_thenOutputTaskName() {
        String expected = "Test Deadline";
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
        String expected = "DEADLINE";
        String taskType = TASK.getType();
        assertEquals(expected, taskType);
    }

    @Test
    void getDate_whenInvoke_thenOutputTaskDate() {
        LocalDate[] expected = new LocalDate[] {LocalDate.parse("2020-04-05")};
        LocalDate[] taskDate = TASK.getDates();
        assertEquals(expected[0], taskDate[0]);
    }

    @Test
    void markCompleted_whenInvoke_thenMarkTaskComplete() {
        String expected = "complete";
        Task task = TASK.markCompleted();
        String taskStatus = task.getStatus();
        assertEquals(expected, taskStatus);
    }
}
