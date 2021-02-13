package duke.task;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.BadDateArgumentException;
import duke.exception.EmptyArgumentException;

public class DeadlineTest {
    @Test
    public void incorrectParseTest() {
        String[] badList = {"2202 10 31", "May 20 2421", "11 30 2020", "13 8 2000"};
        for (String badString: badList) {
            try {
                new Deadline("Dud Desc.", badString);
                fail("Exception not thrown");
            } catch (EmptyArgumentException e) {
                fail("Wrong Exception Thrown");
            } catch (BadDateArgumentException e) {
                //Pass
            }
        }
    }
    @Test
    public void basicToStringTest() {
        try {
            Deadline d = new Deadline("Dud Desc.", "13 08 2020");
            Assertions.assertEquals("[D][ ]: Dud Desc. (Deadline: August 13, 2020)", d.toString());
            d.setDone();
            Assertions.assertEquals("[D][*]: Dud Desc. (Deadline: August 13, 2020)", d.toString());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should not have exception");
        }
    }
}
