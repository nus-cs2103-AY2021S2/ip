package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void setTodoDone() {
        ToDo dummyToDo = new ToDo("hold those diamond hands");
        dummyToDo.setIsDone(true);
        assertEquals("\u2713",
                dummyToDo.getStatusIcon());
    }

}
