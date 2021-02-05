import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DoneCommandTest {
    @Test
    public void getTypeCheck() {
        assertEquals("done", new DoneCommand(1).getType());
    }
}
