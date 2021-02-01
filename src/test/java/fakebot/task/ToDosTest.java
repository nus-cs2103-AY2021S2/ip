package fakebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDosTest {

    @Test
    public void getTaskName_equal() {
        String taskName = "Test";
        ToDos todo = new ToDos(taskName);
        assertEquals(taskName, todo.getTaskName(), "Task Name does not match");
    }

    @Test
    public void markComplete_success() {
        ToDos todo = new ToDos("Test");
        todo.markComplete();
        assertEquals(true, todo.isComplete(), "Task not completed");
    }
}
