import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeListTest {
    @Test
    public void addTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("Live"));
        assertEquals(1 , list.getSize());
    }
    @Test
    public void deleteTest() {
        DukeList list = new DukeList();
        list.add(new ToDos("a"));
        list.add(new ToDos("b"));
        list.deleteAll();
        assertEquals(0, list.getSize());
    }
}
