package core.task;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void todoStringTest() {
        String y = "Some Task Todo";
        Task x = new Todo(y);
        assertEquals("[T]" + "[" + x.getStatusIcon() + "] " + y, x.toString());
    }
}
