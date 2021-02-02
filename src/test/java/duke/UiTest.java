package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class UiTest {
    @Test
    public void dummyTest() {
        Ui ui = new Ui();
        String[] strArr = new String[] {"hello", "sup", "hi"};
        String linePartition = "__________________________________________________________________"
                + "_______________________________________________________"
                + "_____________________________________________________________"
                + System.lineSeparator();
        assertEquals(ui.generateMessage(strArr), linePartition + "hello" + System.lineSeparator()
                + "sup" + System.lineSeparator() + "hi"
                + System.lineSeparator() + linePartition);
    }
}
