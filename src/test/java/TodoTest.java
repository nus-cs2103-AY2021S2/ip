import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TodoTest {
  @Test
  public void todoTest() {
    assertEquals("[T][ ] complete cs2103", new Todo("complete cs2103").toString());
  }
}
