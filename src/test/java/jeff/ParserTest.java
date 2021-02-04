package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void executeTest() {
        try {
            boolean result = Parser.execute("deadline go to school, by 2023-01-01 23:59",
                    new TaskList(), new Ui(), new Storage("data.txt"));
            assertEquals(false, result);
        } catch (JeffException e) {
            fail();
        }
    }
}
