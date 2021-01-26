package core.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest{
    @Test
    public void containsTest() {
        Task t = new Task("hello hello");
        assertEquals(true, t.contains("hello"));
    }
}
