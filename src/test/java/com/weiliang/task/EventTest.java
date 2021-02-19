package com.weiliang.task;

import com.weiliang.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    
    @Test
    public void eventTest() throws DukeException {
        Task task = new Event("test", "2000-01-01T23:59:59");
        task.markComplete();
        assertEquals(task.toString(), "[E][X] test (at: 01 January 2000, 11:59PM)");
    }

}
