package com.tjtanjin.ip;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("Test Task", "incomplete", "TODO");

    @Test
    void getTaskName_whenInvoke_thenOutputTaskName() {
        String expected = "Test Task";
        String taskName = task.getTaskName();
        assertEquals(expected, taskName);
    }

    @Test
    void getStatus_whenInvoke_thenOutputTaskStatus() {
        String expected = "incomplete";
        String taskStatus = task.getStatus();
        assertEquals(expected, taskStatus);
    }

    @Test
    void getType_whenInvoke_thenOutputTaskType() {
        String expected = "TODO";
        String taskType = task.getType();
        assertEquals(expected, taskType);
    }

    @Test
    void getDate_whenInvoke_thenOutputTaskDate() {
        LocalDate expected = null;
        LocalDate taskDate = task.getDate();
        assertEquals(expected, taskDate);
    }

    @Test
    void markCompleted() {
        String expected = "complete";
        task.markCompleted();
        String taskStatus = task.getStatus();
        assertEquals(expected, taskStatus);
    }
}
