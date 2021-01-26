package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void descriptionTest(){
        Task t = new Todo("Homework");
        assertEquals("Homework", t.getDescription());
    }
    @Test
    public void isDoneTest(){
        Task t = new Todo("Homework");
        t.done();
        assertEquals("T", t.getIsDone());
    }
    @Test
    public void typeTest(){
        Task t = new Todo("Homework");
        assertEquals("todo", t.getType());
    }

}
