package duke.customClass;

import org.junit.jupiter.api.Test;
import duke.customClass.Command;

import static org.junit.jupiter.api.Assertions.*;

class CommandRouterTest {

    @Test
    public void testIsDoneDefaultFalse() {
        CommandRouter cr = new CommandRouter();
        assertEquals(false, cr.isExit());
    }

}