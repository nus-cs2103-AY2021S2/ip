package com.weiliang.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void taskTest() {
        Task task = new Task("test");
        assertEquals(task.toString(), "[T][ ] test");
    }

}
