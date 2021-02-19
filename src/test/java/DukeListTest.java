import org.junit.jupiter.api.Test;
import duke.DukeList;
import duke.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeListTest {

    @Test
    public void getSizeTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("test1"));
        list.add(new ToDos("test2"));
        assertEquals(2, list.getSize());
    }

    @Test
    public void addTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("Live"));
        assertEquals(1, list.getSize());
    }

    @Test
    public void deleteTest() {
        DukeList list = new DukeList();
        list.add(new ToDos(("test")));
        list.add(new ToDos(("test2")));
        list.delete(0);
        assertEquals(1, list.getSize());
    }

    @Test
    public void deleteAllTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("a"));
        list.add(new ToDos("b"));
        list.deleteAll();
        assertEquals(0, list.getSize());
    }

    @Test
    public void markAsDoneTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("test"));
        list.markAsDone(0);
        assertEquals(true, list.get(0).isTaskDone());
    }
}
