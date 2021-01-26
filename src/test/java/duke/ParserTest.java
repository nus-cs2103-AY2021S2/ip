package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {
    @Test
    public void todo_correctInput_success() throws Exception {
        String input = "[T] [ ] deadline bro /by 20 Nov 2020";
        assertEquals("[T] [ ] deadline bro /by 20 Nov 2020", Parser.stringToTask(input).toString());
    }

}
