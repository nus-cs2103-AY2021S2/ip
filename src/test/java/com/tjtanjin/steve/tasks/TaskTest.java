package com.tjtanjin.steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import com.tjtanjin.steve.tasks.Task;
import org.junit.jupiter.api.Test;

public class TaskTest {
    private final Task task = new Task("Test Task", "incomplete", "TODO");

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
        LocalDate[] expected = new LocalDate[1];
        LocalDate[] taskDate = task.getDates();
        assertEquals(expected[0], taskDate[0]);
    }

    @Test
    void markCompleted() {
        String expected = "complete";
        task.markCompleted();
        String taskStatus = task.getStatus();
        assertEquals(expected, taskStatus);
    }
}
