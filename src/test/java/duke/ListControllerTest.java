package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.controller.ListController;

public class ListControllerTest {
    @Test
    public void checkValidIndexForListOperation_invalidIndexTest() {
        assertEquals(false, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_DELETE.getCommand(), "1", ModelForTest.TASKLIST_EMPTY));
        assertEquals(false, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_DONE.getCommand(), "1", ModelForTest.TASKLIST_EMPTY));
        assertEquals(false, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_TAG.getCommand(), "1", ModelForTest.TASKLIST_EMPTY));
    }

    @Test
    public void checkValidIndexForListOperation_validIndexTest() {
        assertEquals(true, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_DELETE.getCommand(), "1", ModelForTest.TASKLIST_NOT_EMPTY));
        assertEquals(true, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_DONE.getCommand(), "1", ModelForTest.TASKLIST_NOT_EMPTY));
        assertEquals(true, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_TAG.getCommand(), "1", ModelForTest.TASKLIST_NOT_EMPTY));
        assertEquals(true, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_DEADLINE.getCommand(), "return book", ModelForTest.TASKLIST_EMPTY));
        assertEquals(true, ListController.checkValidIndexForListOperation
                (ModelForTest.PARSER_LIST.getCommand(), null, ModelForTest.TASKLIST_EMPTY));
    }
}
