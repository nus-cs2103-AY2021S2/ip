package fakebot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    public void getTaskName_equal() {
        String taskName = "Test";
        Todo todo = new Todo(taskName);
        assertEquals(taskName, todo.getTaskName(), "Task Name does not match");
    }

    @Test
    public void markComplete_success() {
        Todo todo = new Todo("Test");
        todo.markComplete();
        assertEquals(true, todo.isComplete(), "Task not completed");
    }
}
