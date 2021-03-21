package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.controller.Parser;
import duke.controller.UiController;
import duke.model.Command;
import duke.model.exception.DukeException;
import org.junit.jupiter.api.Test;

public class UiControllerTest {
    @Test
    public void generateTextForUpdate_checkCorrectText() {
        assertEquals(String.format(UiController.MESSAGE_PREDEFINED_FOR_TASKS,
                ModelForTest.PARSER_DEADLINE_TASK.get().toString(), 1),
                ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_DEADLINE, ModelForTest.PARSER_DEADLINE_TASK));
        assertEquals(UiController.MESSAGE_DONE + ModelForTest.PARSER_DONE_TASK.get().toString(),
                ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_DONE, ModelForTest.PARSER_DONE_TASK));
        assertEquals(String.format(UiController.MESSAGE_DELETE,
                ModelForTest.TASK_FOR_TEST.toString(), 1), ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_DELETE, ModelForTest.PARSER_DELETE_TASK));
        assertEquals(DukeException.UnknownCommandException.UNKNOWN_COMMAND_MSG,
                ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_ERROR, ModelForTest.PARSER_ERROR_TASK));
        assertEquals(UiController.MESSAGE_LIST + "\n1." + ModelForTest.TASK_FOR_TEST.toString(),
                ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_LIST, ModelForTest.PARSER_LIST_TASK));
        assertEquals(String.format(UiController.MESSAGE_PREDEFINED_FOR_TASKS,
                ModelForTest.PARSER_TODO_TASK.get().toString(), 1), ModelForTest.UI_CONTROLLER_FOR_TEST.generateTextForUpdate
                (ModelForTest.PARSER_TODO, ModelForTest.PARSER_TODO_TASK));
    }
}
