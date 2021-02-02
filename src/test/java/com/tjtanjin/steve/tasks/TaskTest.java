package com.tjtanjin.steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private final Task TASK = new Task("Test Task", "incomplete", "TODO");

    @Test
    void getTaskName_whenInvoke_thenOutputTaskName() {
        String expected = "Test Task";
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
        String expected = "TODO";
        String taskType = TASK.getType();
        assertEquals(expected, taskType);
    }

    @Test
    void getDate_whenInvoke_thenOutputTaskDate() {
        LocalDate[] expected = new LocalDate[1];
        LocalDate[] taskDate = TASK.getDates();
        assertEquals(expected[0], taskDate[0]);
    }

    @Test
    void markCompleted() {
        String expected = "complete";
        TASK.markCompleted();
        String taskStatus = TASK.getStatus();
        assertEquals(expected, taskStatus);
    }
}
