import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void correctTaskFormat_typesOfTasks_formatOfTask() {
        assertEquals("todo *description of task*", Ui.correctTaskFormat("todo"));
    }

    @Test
    public void invalidNumberExceptionMessage_correctMessageOutput() {
        assertEquals("ERROR! D: Give a number greater than zero and smaller than the total number of tasks.\n",
                Ui.invalidNumberExceptionMessage());
    }

    @Test
    public void invalidKeywordExceptionMessage_correctMessageOutput() {
        assertEquals("ERROR! D: The supported keywords are: todo, deadline, event, done, list, delete, bye, tag only."
                + "\n", Ui.invalidKeywordExceptionMessage());
    }

}
