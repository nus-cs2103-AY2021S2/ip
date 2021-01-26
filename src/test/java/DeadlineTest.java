import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

  @Test
  public void DeadlineTest(){
    assertEquals("[D][ ] deadline return book (by: Dec 02 2019)", new Deadline("deadline return book","2/12/2019 1800").toString());
  }
}

