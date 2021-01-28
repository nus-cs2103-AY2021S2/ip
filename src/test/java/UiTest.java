import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String SPACE = "     ";
    private static final String LINE = "\n     <<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>\n";

    @Test
    public void CorrectTaskFormat_TypesOfTasks_FormatOfTask() {
        assertEquals("todo *description of task*", Ui.CorrectTaskFormat("todo"));
    }

    @Test
    public void InvalidNumberExceptionMessage_CorrectMessageOutput() {
        assertEquals(LINE + SPACE + "ERROR! D: Please give a number greater than zero and smaller than the total number of tasks" + LINE,
                Ui.InvalidNumberExceptionMessage());
    }

    @Test
    public void InvalidKeywordExceptionMessage_CorrectMessageOutput() {
        assertEquals(LINE + SPACE + "The supported keywords are: todo, deadline, event, done, list, delete, bye only." + LINE,
                Ui.InvalidKeywordExceptionMessage());
    }

}
