package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void executeTest() {
        try {
            Command result = Parser.parse("deadline go to school, by 2023-01-01 23:59");
            assertEquals(new CommandDeadline("go to school", " by 2023-01-01 23:59"), result);
        } catch (JeffException e) {
            fail();
        }
    }
}
