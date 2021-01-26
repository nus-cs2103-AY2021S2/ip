import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadLinetest {

  @Test
  public void deadLinetest() {
    assertEquals("[D][ ] deadline return book (by: Dec 02 2019)",
            new Deadline("deadline return book", "2/12/2019 1800").toString());
  }
}

