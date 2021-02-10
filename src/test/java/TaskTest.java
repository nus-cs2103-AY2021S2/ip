import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toDoCreationTest() {
        assertEquals(new ToDo("testTask").generateDataString(), "todo notDone testTask");
    }

    @Test
    public void deadlineCreationTest() {
        assertEquals(new Deadline("testDeadline", "2020-02-20 2020").generateDataString(),
                "deadline 2020-02-20 2020 notDone testDeadline");
    }
    @Test
    public void eventCreationTest() {
        assertEquals(new Event("testEvent", "2020-02-20 2020").generateDataString(),
                "event 2020-02-20 2020 notDone testEvent");
    }
}
