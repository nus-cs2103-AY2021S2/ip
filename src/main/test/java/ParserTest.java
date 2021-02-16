import Duke.Parser;
import Duke.StringDatePair;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void inputEventTest(){
        StringDatePair output = new Parser().parse(
            "event Test Event /at 2020-10-28 1000",
            Parser.commandType.INPUT_EVENT
        );
        assertEquals("Test Event ", output.getString());
        assertEquals(LocalDateTime.of(2020, 10, 28, 10, 0)
            , output.getDate());
    }

    @Test
    public void inputDeadlineTest(){
        StringDatePair output = new Parser().parse(
            "deadline return book /by 2020-03-10 1800",
            Parser.commandType.INPUT_DEADLINE
        );
        assertEquals("return book ", output.getString());
        assertEquals(LocalDateTime.of(2020, 3, 10, 18, 0)
            , output.getDate());
    }
}