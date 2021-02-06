package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void executeTest() {
        try {
            String result = Parser.execute("deadline go to school, by 2023-01-01 23:59",
                    new TaskList(), new Storage("data.txt"));
            assertEquals("Got it. I've added this task:\n[D][ ] go to school (by: 1 JANUARY 2023 23:59)\n" +
                    "Now you have 1 tasks in the list.", result);
        } catch (JeffException e) {
            fail();
        }
    }
}
