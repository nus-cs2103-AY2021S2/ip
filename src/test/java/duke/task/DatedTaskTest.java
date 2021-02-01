package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DatedTaskTest {

    @Test
    public void deadlineDateInputOutput_correctInput_success() throws TaskException {
        DatedTask dl = new Deadline("Test description", "Nov 20 2020");
        assertEquals("[D] [ ] Test description (by: Nov 20 2020)", dl.toString());
    }

    @Test
    public void eventDateInputOutput_correctInput_success() throws TaskException {
        DatedTask dl = new Event("Test description", "Nov 20 2020");
        assertEquals("[E] [ ] Test description (at: Nov 20 2020)", dl.toString());
    }


}
