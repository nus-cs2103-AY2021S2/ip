import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

  @Test
  public void todoTest(){
    assertEquals("[T][ ] complete cs2103", new Todo("complete cs2103").toString());
  }
}
