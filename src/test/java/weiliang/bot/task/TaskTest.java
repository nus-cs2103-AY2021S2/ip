package weiliang.bot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    
    @Test
    public void taskTest() {
        Task task = new Task("test");
        assertEquals(task.toString(), "[T][ ] test");
    }

}
