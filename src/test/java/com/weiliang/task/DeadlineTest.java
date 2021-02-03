package com.weiliang.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    
    @Test
    public void taskTest() {
        Task task = new Deadline("test", "2000-01-01T23:59:59");
        task.complete();
        assertEquals(task, "[T][X] test (by: 01 January 2000, 11:59PM)");
    }

}
