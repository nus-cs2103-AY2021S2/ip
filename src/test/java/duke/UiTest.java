package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void dummyTest() {
        Ui ui = new Ui();
        String[] strArr = new String[] {"hello", "sup", "hi"};
        String LINE_PARTITION =
                "__________________________________________________________________" +
                        "_______________________________________________________"
                        + "_____________________________________________________________"
                        + System.lineSeparator();
        assertEquals(ui.generateMessage(strArr),
                LINE_PARTITION + "hello" + System.lineSeparator() +
                        "sup" + System.lineSeparator() + "hi" +
                        System.lineSeparator() + LINE_PARTITION);
    }
}
