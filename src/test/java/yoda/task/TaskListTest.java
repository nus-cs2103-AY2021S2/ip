package yoda.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


class TaskListTest {
    private TaskList tasks = new TaskList(new ArrayList<>());

    @Test
    void testAddTask() throws InvalidTaskListIndexException {
        ToDo todo = new ToDo("have a break");
        tasks.addTask(todo);
        assertEquals(todo, tasks.accessTask(0));
    }


}
