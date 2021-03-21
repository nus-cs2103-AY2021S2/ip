package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.controller.Parser;
import duke.model.Command;
import duke.model.task.ListItem;
import duke.model.task.TaskList;
import duke.model.task.Todo;

public class ParserTest {
    private static final TaskList TASKLIST_FOR_TEST = new TaskList();
    private static final ListItem[] TASK_FOR_TASKLIST = {new Todo("test tag command")};
    private static final TaskList TASKLIST_FOR_TAG = new TaskList(Arrays.asList(TASK_FOR_TASKLIST));
    private static final Parser PARSER_DEADLINE =
            Parser.createParser("deadline return book /by Sunday", TASKLIST_FOR_TEST);
    private static final Parser PARSER_EVENT = Parser.createParser("event team meeting /at 2:00pm", TASKLIST_FOR_TEST);
    private static final Parser PARSER_ERROR = Parser.createParser("unknown", TASKLIST_FOR_TEST);
    private static final Parser PARSER_LIST = Parser.createParser("list", TASKLIST_FOR_TEST);
    private static final Parser PARSER_TAG = Parser.createParser("tag 1 nice", TASKLIST_FOR_TAG);
    private static final Parser PARSER_TODO = Parser.createParser("todo submit ip jar", TASKLIST_FOR_TEST);


    @Test
    public void parseWithFormatCheck_checkCommand() {
        assertEquals(Command.DEADLINE, PARSER_DEADLINE.getCommand());
        assertEquals(Command.EVENT, PARSER_EVENT.getCommand());
        assertEquals(Command.ERROR, PARSER_ERROR.getCommand());
        assertEquals(Command.LIST, PARSER_LIST.getCommand());
        assertEquals(Command.TODO, PARSER_TODO.getCommand());
        assertEquals(Command.TAG, PARSER_TAG.getCommand());
    }

    @Test
    public void parseWithFormatCheck_checkArgument() {
        assertEquals("return book", PARSER_DEADLINE.getArgument());
        assertEquals("team meeting", PARSER_EVENT.getArgument());
        assertEquals("submit ip jar", PARSER_TODO.getArgument());
        assertEquals(null, PARSER_LIST.getArgument());
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", PARSER_ERROR.getArgument());
        assertEquals("1", PARSER_TAG.getArgument());
    }

    @Test
    public void parseWithFormatCheck_checkOptionalArgument() {
        assertEquals("Sunday", PARSER_DEADLINE.getOptionalArgument());
        assertEquals("2:00pm", PARSER_EVENT.getOptionalArgument());
        assertEquals(null, PARSER_TODO.getOptionalArgument());
        assertEquals(null, PARSER_LIST.getOptionalArgument());
        assertEquals(null, PARSER_ERROR.getOptionalArgument());
        assertEquals("nice", PARSER_TAG.getOptionalArgument());
    }
}
