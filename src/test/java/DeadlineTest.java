
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.TaskException;
import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void checkStrings() {
        try {
            String output = new Deadline("test", "2020-01-03").toString();
            String output1 = new Deadline("test", "2020-01-03").toSaveFormat();

            String output2 = new Deadline("test", "03-01-2020").toString();
            assertEquals("[D][ ] test (by: Jan 3 2020)", output);
            assertEquals("D|0|test|2020-01-03", output1);
            assertEquals(output2, output);
        } catch (TaskException e) {
            // do nothing
        }
    }

    @Test
    public void checkDoneStateChange() {
        try {
            Deadline deadline = new Deadline("test", "2020-01-03");
            deadline.changeTaskToDone();

            String output1 = deadline.toString();
            String output2 = deadline.toSaveFormat();

            assertEquals(output1, "[D][X] test (by: Jan 3 2020)");
            assertEquals("D|1|test|2020-01-03", output2);


        } catch (TaskException e) {
            // do nothing
        }
    }

}
