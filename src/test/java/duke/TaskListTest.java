package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void taskList_SetDone_Sucess() {
        TaskList temp = new TaskList();
        temp.add(new Task("Test", "Test"));
        temp.setDone(0);
        assertEquals("[[Test][X] Test]", temp.toString());
    }
}
