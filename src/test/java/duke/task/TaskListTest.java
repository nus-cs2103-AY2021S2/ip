package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList lst;

    @BeforeEach
    void init() {
        lst = new TaskList();
    }

    @Test
    void add_validTask_success() {
        Task todo = new Todo("test todo");
        lst.add(todo);

        assertEquals(1, lst.size());
        assertEquals(todo, lst.get(0));
    }

    @Test
    void get_validTask_success() {
        Task todo1 = new Todo("test todo 1");
        Task todo2 = new Todo("test todo 2");
        lst.add(todo1);
        lst.add(todo2);

        assertEquals(todo1, lst.get(0));
        assertEquals(todo2, lst.get(1));
    }
}
